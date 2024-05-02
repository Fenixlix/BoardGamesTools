package com.example.boardgamestools.tool_triomino.viewmodel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardgamestools.core.data.roomData.TriominoPlayerDAO
import com.example.boardgamestools.core.data.roomData.TriominoPlayerEntity
import com.example.boardgamestools.tool_triomino.model.TriominoEvent
import com.example.boardgamestools.tool_triomino.model.TriominoRanking
import com.example.boardgamestools.tool_triomino.model.TriominoRanks
import com.example.boardgamestools.tool_triomino.model.TriominoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriominoViewModel @Inject constructor(
    private val playerDAO: TriominoPlayerDAO,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val roundKey = intPreferencesKey("round")
    private val turnKey = intPreferencesKey("turn")
    private var initOneTimeFlag = true

    private val _state = MutableStateFlow(TriominoState())

    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TriominoState()
    )

    init {
        viewModelScope.launch {
            playerDAO.getAll().collect { newList ->
                if (initOneTimeFlag) {
                    calculateRanking(players = newList)
                    initOneTimeFlag = false
                }
                _state.update {
                    it.copy(
                        players = newList.map { player ->
                            player.copy(
                                rank = TriominoRanks.getPlace(
                                    _state.value.ranking,
                                    player.score
                                )
                            )
                        }
                    )
                }
            }
        }
    }

    fun onEvent(event: TriominoEvent) {
        when (event) {
            TriominoEvent.ContinueGame -> {
                continueGame()
            }

            TriominoEvent.NewGame -> {
                newGame()
            }

            is TriominoEvent.AddPlayer -> {
                addPlayer(event.player)
            }

            is TriominoEvent.PassTurn -> {
                passPlayerTurn(_state.value.players[_state.value.turn])
                nextTurn()
                saveTurn()
            }

            is TriominoEvent.FinishRound -> {
                passPlayerTurn(_state.value.players[_state.value.turn], extra = event.extra)
                nextRound()
                saveRound()
            }

            TriominoEvent.TogglePlayersDialog -> {
                _state.update { it.copy(addPlayerDialog = _state.value.addPlayerDialog.not()) }
            }

            TriominoEvent.ToggleEndRoundDialog -> {
                _state.update { it.copy(endRoundDialog = _state.value.endRoundDialog.not()) }
            }

            is TriominoEvent.ScoreSelected -> {
                _state.update { it.copy(scorePoints = event.score) }
            }

            is TriominoEvent.BonusSelected -> {
                _state.update { it.copy(bonusPoints = event.bonus) }
            }

            is TriominoEvent.PenaltySelected -> {
                _state.update { it.copy(penaltyPoints = event.penalty) }
            }
        }
    }

    // ----- state related functions
    private fun nextTurn() {
        var nextTurn = _state.value.turn + 1
        if (nextTurn > _state.value.players.size - 1) {
            nextTurn = 0
        }
        _state.update { it.copy(turn = nextTurn) }
    }

    private fun nextRound() {
        // Select the lowest score player to be the first player to play in the next round
        var smallestScore = _state.value.ranking.third
        var nextTurn = 0
        _state.value.players.forEachIndexed { index, player ->
            if (player.score <= smallestScore) {
                smallestScore = player.score
                nextTurn = index
            }
        }
        _state.update {
            it.copy(
                round = _state.value.round + 1,
                turn = nextTurn
            )
        }
    }

    private fun updateRanking(newRanking: TriominoRanking) {
        _state.update {
            it.copy(
                ranking = newRanking,
                scorePoints = null,
                bonusPoints = null,
                penaltyPoints = null
            )
        }
    }

    // ----- DataStore related functions
    private fun saveTurn() = viewModelScope.launch {
        dataStore.edit {
            it[turnKey] = _state.value.turn
        }
    }

    private fun saveRound() = viewModelScope.launch {
        dataStore.edit {
            it[roundKey] = _state.value.round
        }
        saveTurn()
    }

    private fun newGame() = viewModelScope.launch {
        dataStore.edit {
            it[roundKey] = 0
            it[turnKey] = 0
        }
        _state.update { it.copy(continueOrNewGameDialog = false) }
        playerDAO.deleteAll()
    }

    private fun continueGame() = viewModelScope.launch {
        dataStore.data.first { pref ->
            _state.update {
                it.copy(
                    turn = pref[turnKey] ?: 0,
                    round = pref[roundKey] ?: 0,
                    continueOrNewGameDialog = false
                )
            }
            true
        }
    }

    private fun calculateRanking(players: List<TriominoPlayerEntity>) {
        var finalRanking = TriominoRanking()
        players.forEach {
            finalRanking = TriominoRanks.getNewRanking(finalRanking, it.score)
        }
        _state.update {
            it.copy(
                ranking = finalRanking
            )
        }
    }

    // ----- Room related functions -----
    private fun addPlayer(player: TriominoPlayerEntity) = viewModelScope.launch {
        playerDAO.insertNewPlayer(player)
    }

    private fun passPlayerTurn(player: TriominoPlayerEntity, extra: Int = 0) =
        viewModelScope.launch {
            val score = state.value.scorePoints ?: 0
            val bonus = state.value.bonusPoints ?: 0
            val penalty = state.value.penaltyPoints ?: 0
            val newScore =
                player.score + score + bonus - penalty + extra

            val newRanking = TriominoRanks.getNewRanking(
                ranking = _state.value.ranking,
                score = newScore
            )
            updateRanking(newRanking)
            playerDAO.updateScore(
                player.copy(
                    score = newScore
                )
            )
        }
}