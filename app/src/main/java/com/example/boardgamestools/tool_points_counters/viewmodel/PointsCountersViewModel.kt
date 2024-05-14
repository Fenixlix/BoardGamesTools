package com.example.boardgamestools.tool_points_counters.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardgamestools.core.data.model.CounterBGT
import com.example.boardgamestools.core.data.model.counterSplit
import com.example.boardgamestools.core.data.model.toSaveString
import com.example.boardgamestools.core.data.roomData.PointsPlayerDao
import com.example.boardgamestools.core.data.roomData.PointsPlayerEntity
import com.example.boardgamestools.tool_points_counters.model.PointsCountersEvent
import com.example.boardgamestools.tool_points_counters.model.PointsCountersPlayer
import com.example.boardgamestools.tool_points_counters.model.PointsCountersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PointsCountersViewModel @Inject constructor(
    private val dao: PointsPlayerDao
) : ViewModel() {

    var state by mutableStateOf(PointsCountersState())
        private set

    init {
        viewModelScope.launch {
            dao.getAll().collect { updatedList ->
                state = if (updatedList.isEmpty()) {
                    state.copy(
                        playersList = emptyList(),
                        showAddPlayersScreen = true
                    )
                } else {
                    state.copy(
                        playersList = updatedList.map {
                            it.toPointsCounterPlayer()
                        }
                    )
                }
            }
        }
    }

    fun onEvent(event: PointsCountersEvent) {
        when (event) {
            PointsCountersEvent.NewGame -> viewModelScope.launch {
                dao.deleteAll()
                state = state.copy(
                    showContinueOrNewGameDialog = false
                )
            }

            PointsCountersEvent.CloseContinueOrNewGameDialog -> {
                state = state.copy(
                    showContinueOrNewGameDialog = state.showContinueOrNewGameDialog.not()
                )
            }

            PointsCountersEvent.ClosePointsEditDialog -> {
                state = state.copy(
                    showPointsEditDialog = false,
                    counterVessel = null,
                    rotationVessel = null
                )
            }

            is PointsCountersEvent.OpenPointsEditDialog -> {
                state = state.copy(
                    showPointsEditDialog = true,
                    counterVessel = event.counter,
                    rotationVessel = event.rotation
                )
            }

            is PointsCountersEvent.UpdateCounter -> {
                state = state.copy(
                    playersList = state.playersList.map {
                        if (it.id == event.newCounter.ownerId) {
                            it.counterList.map { counter ->
                                if (counter.id == event.newCounter.id) {
                                    counter.points = event.newCounter.points
                                }
                            }
                            savePlayerData(it)
                        }
                        it
                    },
                    showPointsEditDialog = false
                )
            }

            is PointsCountersEvent.AddPlayer -> viewModelScope.launch {
                savePlayerData(event.player)
            }

            PointsCountersEvent.CloseAddPlayersScreen -> {
                state = state.copy(showAddPlayersScreen = false)
            }
        }
    }

    private fun savePlayerData(player: PointsCountersPlayer) = viewModelScope.launch {
        dao.upsertPlayer(player.toEntity())
    }

}

private fun PointsPlayerEntity.toPointsCounterPlayer() = PointsCountersPlayer(
    id = this.id,
    name = this.name,
    orientation = this.orientation,
    counterList = this.createCounterList()
)

private fun PointsCountersPlayer.toEntity(): PointsPlayerEntity {
    var counters = ""
    this.counterList.forEach {
        counters += it.toSaveString()
    }
    val result = PointsPlayerEntity(
        id = this.id,
        name = this.name,
        counters = counters,
        orientation = this.orientation
    )
    return result
}

private fun PointsPlayerEntity.createCounterList(): List<CounterBGT> {
    val newCounterList = mutableListOf<CounterBGT>()
    val separatedCounters = this.counters.counterSplit()
    separatedCounters.forEachIndexed { index, pair ->
        newCounterList.add(
            element = CounterBGT(
                owner = this.name,
                ownerId = this.id,
                id = index,
                name = pair.first,
                points = pair.second
            )
        )
    }
    return newCounterList
}