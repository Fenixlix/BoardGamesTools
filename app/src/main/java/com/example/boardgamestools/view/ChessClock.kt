package com.example.boardgamestools.view

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.boardgamestools.R
import com.example.boardgamestools.model.utilities.chess_clock.ChessGameEvent
import com.example.boardgamestools.viewmodel.ChessClockViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChessClock : AppCompatActivity() {

    private val chessClockViewModel by viewModels<ChessClockViewModel>()

    private lateinit var player1Timer: TextView
    private lateinit var player2Timer: TextView
    private lateinit var player1Switch: RadioButton
    private lateinit var player2Switch: RadioButton
    private lateinit var chessButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chess_clock)

        player1Timer = findViewById(R.id.chessPlayer1Timer)
        player2Timer = findViewById(R.id.chessPlayer2Timer)
        player1Switch = findViewById(R.id.chessPlayer1Switch)
        player2Switch = findViewById(R.id.chessPlayer2Switch)
        chessButton = findViewById(R.id.chessButton)

        var gameIsOn = false
        chessClockViewModel.gameIsOn.observe(this) {
            when (it) {
                true -> {
                    gameIsOn = true
                    chessButton.isEnabled = true
                    chessButton.text = getString(R.string.checkmate)
                }
                false -> {
                    gameIsOn = false
                    chessButton.isEnabled = true
                    chessButton.text = getString(R.string.reset_game)
                }
                null -> {
                    chessButton.isEnabled = false
                }
            }
        }

        chessClockViewModel.chessGameState.observe(this) {
            when (it.p1Turn) {
                true -> {
                    player1Timer.text = it.p1Time.toTime()
                }
                false -> {
                    player2Timer.text = it.p2Time.toTime()
                }
                null -> {
                    player1Timer.text = it.p1Time.toTime()
                    player2Timer.text = it.p2Time.toTime()
                    player1Switch.isChecked = false
                    player2Switch.isChecked = false
                }
            }
        }

        chessButton.setOnClickListener {
            if (gameIsOn) {
                chessClockViewModel.onEvent(ChessGameEvent.Checkmate)
            } else {
                chessClockViewModel.onEvent(ChessGameEvent.GameReset)
            }
        }

        player1Switch.setOnClickListener {
            chessClockViewModel.onEvent(ChessGameEvent.P2Turn)
        }

        player2Switch.setOnClickListener {
            chessClockViewModel.onEvent(ChessGameEvent.P1Turn)
        }
    }
}

// Extension function to get a time format from an Int with the total seconds count
fun Int.toTime(): String {
    return "${this / 60}:${this % 60}"
}


