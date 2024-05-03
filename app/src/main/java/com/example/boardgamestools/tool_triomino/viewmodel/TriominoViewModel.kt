package com.example.boardgamestools.tool_triomino.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.flow.first
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

    var state by mutableStateOf(TriominoState())
        private set

    init {
        viewModelScope.launch {
            playerDAO.getAll().collect { newList ->
                if (initOneTimeFlag) {
                    calculateRanking(players = newList)
                    initOneTimeFlag = false
                }
                state = state.copy(
                    players = newList.map { player ->
                        player.copy(
                            rank = TriominoRanks.getPlace(
                                state.ranking,
                                player.score
                            )
                        )
                    }
                )
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
                passPlayerTurn(state.players[state.turn])
                nextTurn()
                saveTurn()
            }

            is TriominoEvent.FinishRound -> {
                passPlayerTurn(state.players[state.turn], extra = event.extra)
                nextRound()
                saveRound()
                state = state.copy(endRoundDialog = state.endRoundDialog.not())
            }

            TriominoEvent.TogglePlayersDialog -> {
                state = state.copy(addPlayerDialog = state.addPlayerDialog.not())
            }

            TriominoEvent.ToggleEndRoundDialog -> {
                state = state.copy(endRoundDialog = state.endRoundDialog.not())
            }

            is TriominoEvent.ScoreSelected -> {
                state = state.copy(scorePoints = event.score)
            }

            is TriominoEvent.BonusSelected -> {
                state = state.copy(bonusPoints = event.bonus)
            }

            is TriominoEvent.PenaltySelected -> {
                state = state.copy(penaltyPoints = event.penalty)
            }
        }
    }

    // ----- state related functions
    private fun nextTurn() {
        var nextTurn = state.turn + 1
        if (nextTurn > state.players.size - 1) {
            nextTurn = 0
        }
        state = state.copy(turn = nextTurn)
    }

    private fun nextRound() {
        // Select the lowest score player to be the first player to play in the next round
        var smallestScore = state.ranking.third
        var nextTurn = 0
        state.players.forEachIndexed { index, player ->
            if (player.score <= smallestScore) {
                smallestScore = player.score
                nextTurn = index
            }
        }
        state = state.copy(
            round = state.round + 1,
            turn = nextTurn
        )
    }

    private fun updateRanking(newRanking: TriominoRanking) {
        state = state.copy(
            ranking = newRanking,
            scorePoints = null,
            bonusPoints = null,
            penaltyPoints = null
        )
    }

    // ----- DataStore related functions
    private fun saveTurn() = viewModelScope.launch {
        dataStore.edit {
            it[turnKey] = state.turn
        }
    }

    private fun saveRound() = viewModelScope.launch {
        dataStore.edit {
            it[roundKey] = state.round
        }
        saveTurn()
    }

    private fun newGame() = viewModelScope.launch {
        dataStore.edit {
            it[roundKey] = 0
            it[turnKey] = 0
        }
        state = state.copy(continueOrNewGameDialog = false)
        playerDAO.deleteAll()
    }

    private fun continueGame() = viewModelScope.launch {
        dataStore.data.first { pref ->
            state = state.copy(
                turn = pref[turnKey] ?: 0,
                round = pref[roundKey] ?: 0,
                continueOrNewGameDialog = false
            )
            true
        }
    }

    private fun calculateRanking(players: List<TriominoPlayerEntity>) {
        var finalRanking = TriominoRanking()
        players.forEach {
            finalRanking = TriominoRanks.getNewRanking(finalRanking, it.score)
        }
        state = state.copy(ranking = finalRanking)
    }

    // ----- Room related functions -----
    private fun addPlayer(player: TriominoPlayerEntity) = viewModelScope.launch {
        playerDAO.insertNewPlayer(player)
    }

    private fun passPlayerTurn(player: TriominoPlayerEntity, extra: Int = 0) =
        viewModelScope.launch {
            val score = state.scorePoints ?: 0
            val bonus = state.bonusPoints ?: 0
            val penalty = state.penaltyPoints ?: 0
            val newScore =
                player.score + score + bonus - penalty + extra

            val newRanking = TriominoRanks.getNewRanking(
                ranking = state.ranking,
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