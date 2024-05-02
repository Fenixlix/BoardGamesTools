package com.example.boardgamestools.tool_chess_clock.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardgamestools.tool_chess_clock.model.ChessGameEvent
import com.example.boardgamestools.tool_chess_clock.model.ChessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChessClockViewModel @Inject constructor() : ViewModel() {

    var chessGameState by mutableStateOf(ChessState())
        private set

    // Initial time given to each player for the game in seconds
    private val initialTime = 10 * 60
    private fun confirmGameStart() {
        if (chessGameState.gameIsOn == null) {
            chessGameState = chessGameState.copy(
                gameIsOn = true
            )
            startGame()
        }
    }

    private val tic = flow {
        while (true) {
            delay(1000)
            emit(1)
        }
    }

    init {
        chessGameState = chessGameState.copy(
            p1Turn = null,
            p1Time = initialTime,
            p2Time = initialTime
        )
    }

    private fun startGame() {
        viewModelScope.launch {
            tic.collect {
                when (chessGameState.p1Turn) {
                    null -> {
                        if (chessGameState.gameIsOn != null && chessGameState.gameIsOn == false) {
                            this.coroutineContext.cancel(null)
                        }
                    }

                    false -> {
                        chessGameState = chessGameState.copy(
                            p2Time = chessGameState.p2Time - it
                        )
                    }

                    true -> {
                        chessGameState = chessGameState.copy(
                            p1Time = chessGameState.p1Time - it
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: ChessGameEvent) {
        when (event) {
            ChessGameEvent.Checkmate -> {
                chessGameState = chessGameState.copy(
                    p1Turn = null,
                    gameIsOn = false
                )
            }

            ChessGameEvent.GameReset -> {
                chessGameState = chessGameState.copy(
                    p1Turn = null,
                    p1Time = initialTime,
                    p2Time = initialTime,
                    gameIsOn = null
                )
            }

            ChessGameEvent.P1Turn -> {
                chessGameState = chessGameState.copy(
                    p1Turn = true
                )
                confirmGameStart()
            }

            ChessGameEvent.P2Turn -> {
                chessGameState = chessGameState.copy(
                    p1Turn = false
                )
                confirmGameStart()
            }
        }
    }

    // Todo: add configurations and various game modes to play
}

