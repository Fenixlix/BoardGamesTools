package com.example.boardgamestools.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardgamestools.model.utilities.chess_clock.ChessGameEvent
import com.example.boardgamestools.model.utilities.chess_clock.ChessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChessClockViewModel @Inject constructor() : ViewModel() {
    val gameIsOn = MutableLiveData<Boolean?>()
    val chessGameState = MutableLiveData<ChessState>()

    // Initial time given to each player for the game in seconds
    private val initialTime = 30 * 60
    private fun confirmGameStart() {
        if (gameIsOn.value == null) {
            gameIsOn.postValue(true)
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
        chessGameState.postValue(
            ChessState(
                p1Turn = null,
                p1Time = initialTime,
                p2Time = initialTime
            )
        )
    }

    private fun startGame() {
        viewModelScope.launch {
            tic.collect {
                when (chessGameState.value!!.p1Turn) {
                    null -> {
                        if (gameIsOn.value != null && gameIsOn.value == false) {
                            this.coroutineContext.cancel(null)
                        }
                    }
                    false -> {
                        chessGameState.postValue(
                            chessGameState.value!!.copy(
                                p2Time = chessGameState.value!!.p2Time - it
                            )
                        )
                    }
                    true -> {
                        chessGameState.postValue(
                            chessGameState.value!!.copy(
                                p1Time = chessGameState.value!!.p1Time - it
                            )
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: ChessGameEvent) {
        when (event) {
            ChessGameEvent.Checkmate -> {
                gameIsOn.postValue(false)
                chessGameState.postValue(
                    chessGameState.value!!.copy(
                        p1Turn = null
                    )
                )
            }
            ChessGameEvent.GameReset -> {
                gameIsOn.postValue(null)
                chessGameState.postValue(
                    chessGameState.value!!.copy(
                        p1Turn = null,
                        p1Time = initialTime,
                        p2Time = initialTime
                    )
                )
            }
            ChessGameEvent.P1Turn -> {
                chessGameState.postValue(
                    chessGameState.value!!.copy(
                        p1Turn = true
                    )
                )
                confirmGameStart()
            }
            ChessGameEvent.P2Turn -> {
                chessGameState.postValue(
                    chessGameState.value!!.copy(
                        p1Turn = false
                    )
                )
                confirmGameStart()
            }
        }
    }
}

