package com.example.boardgamestools.tool_game_craps.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.util.toKotlinPair
import androidx.lifecycle.ViewModel
import com.example.boardgamestools.R
import com.example.boardgamestools.tool_game_craps.model.CrapsGame
import com.example.boardgamestools.tool_game_craps.model.CrapsState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CrapsViewModel @Inject constructor(
    private val craps : CrapsGame
): ViewModel(){
    var state by mutableStateOf(CrapsState())
        private set

    // it begins with a 2 so that it can differentiate from the start a play
    private var previousGameState = 2

    fun takeTurn(){
        if (craps.gameState() != 0){
            craps.resetGame()
            state = state.copy(
                currentDices = Pair(0,0),
                targetScore = 0,
                title = R.string.game_start_btn,
                gameRules = R.string.craps_first_turn_rules,
                btnMessage = R.string.take_turn
            )
            previousGameState = 2
        } else {
            state = state.copy(
                currentDices = craps.takeTurn().toKotlinPair(),
                targetScore = craps.score
            )
            if (previousGameState != craps.gameState()) updateStatus()
        }
    }

    private fun updateStatus(){
        previousGameState = craps.gameState()
        when(previousGameState){
            1 -> {
                state = state.copy(
                    title = R.string.player_win,
                    btnMessage = R.string.reset_game_btn
                )
            }
            0 -> {
                state = state.copy(
                    title = R.string.game_continue,
                    gameRules = R.string.craps_rest_game_rules,
                    btnMessage = R.string.take_turn
                )
            }
            -1 -> {
                state = state.copy(
                    title = R.string.player_lose,
                    btnMessage = R.string.reset_game_btn
                )
            }
        }
    }
}
