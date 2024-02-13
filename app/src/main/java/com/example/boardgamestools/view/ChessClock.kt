package com.example.boardgamestools.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.boardgamestools.R
import com.example.boardgamestools.databinding.ActivityChessClockBinding
import com.example.boardgamestools.model.utilities.chess_clock.ChessGameEvent
import com.example.boardgamestools.viewmodel.ChessClockViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChessClock : AppCompatActivity() {

    private lateinit var binding: ActivityChessClockBinding
    private val chessClockViewModel by viewModels<ChessClockViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChessClockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gameIsOn = false
        chessClockViewModel.gameIsOn.observe(this) {
            when (it) {
                true -> {
                    gameIsOn = true
                    binding.chessButton.isEnabled = true
                    binding.chessButton.text = getString(R.string.checkmate)
                }
                false -> {
                    gameIsOn = false
                    binding.chessButton.isEnabled = true
                    binding.chessButton.text = getString(R.string.reset_game)
                }
                null -> {
                    binding.chessButton.isEnabled = false
                }
            }
        }

        chessClockViewModel.chessGameState.observe(this) {
            when (it.p1Turn) {
                true -> {
                    binding.chessPlayer1Timer.text = it.p1Time.toTime()
                }
                false -> {
                    binding.chessPlayer2Timer.text = it.p2Time.toTime()
                }
                null -> {
                    binding.chessPlayer1Timer.text = it.p1Time.toTime()
                    binding.chessPlayer2Timer.text = it.p2Time.toTime()
                    binding.chessPlayer1Switch.isChecked = false
                    binding.chessPlayer2Switch.isChecked = false
                }
            }
        }

        binding.chessButton.setOnClickListener {
            if (gameIsOn) {
                chessClockViewModel.onEvent(ChessGameEvent.Checkmate)
            } else {
                chessClockViewModel.onEvent(ChessGameEvent.GameReset)
            }
        }

        binding.chessPlayer1Switch.setOnClickListener {
            chessClockViewModel.onEvent(ChessGameEvent.P2Turn)
        }

        binding.chessPlayer2Switch.setOnClickListener {
            chessClockViewModel.onEvent(ChessGameEvent.P1Turn)
        }
    }
}

// Extension function to get a time format from an Int with the total seconds count
fun Int.toTime(): String {
    return "${this / 60}:${this % 60}"
}


