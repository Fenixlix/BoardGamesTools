package com.example.boardgamestools.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.boardgamestools.R
import com.example.boardgamestools.databinding.ActivityCrapsBinding
import com.example.boardgamestools.simpleGames.CrapsGame
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Craps : AppCompatActivity() {

    private lateinit var binding: ActivityCrapsBinding

    // Craps game variables
    @Inject
    lateinit var crapsGame: CrapsGame
    private var turnScore = 0
    private var targetScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRules(true)

        binding.crapsBtnTossDices.setOnClickListener {
            // todo: learn to make animations based in the time of the button is been press
            if (crapsGame.gameState() != 0) {
                resetGame()
                setRules(true)
            } else {
                setDices(crapsGame.takeTurn())
                updateStatus(crapsGame.gameState())
            }
        }
    }

    private fun updateStatus(status: Int) {
        binding.crapsTvWinLose.text = when (status) {
            1 -> {
                clearData()
                getString(R.string.player_win)
            }
            0 -> {
                if (targetScore == 0) {
                    setRules(false)
                    targetScore = turnScore
                    binding.crapsTvScore.text = getString(R.string.target_score, targetScore)
                }
                getString(R.string.game_continue)
            }
            -1 -> {
                clearData()
                getString(R.string.player_lose)
            }
            else -> getString(R.string.game_start)
        }
    }

    private fun setDices(dices: IntArray) {
        val (dice1, dice2) = dices
        turnScore = dice1 + dice2
        binding.crapsDice1.setImageResource(getDiceImg(dice1))
        binding.crapsDice2.setImageResource(getDiceImg(dice2))
    }

    private fun getDiceImg(point: Int): Int {
        return when (point) {
            1 -> R.drawable.d1
            2 -> R.drawable.d2
            3 -> R.drawable.d3
            4 -> R.drawable.d4
            5 -> R.drawable.d5
            6 -> R.drawable.d6
            else -> R.drawable.craps_logo
        }
    }

    private fun clearData() {
        targetScore = 0
        turnScore = 0
        binding.crapsTvScore.text = getString(R.string.target_score, 0)
        binding.crapsBtnTossDices.text = getString(R.string.reset_game)
    }

    private fun resetGame() {
        updateStatus(2)
        binding.crapsBtnTossDices.text = getString(R.string.take_turn)
        crapsGame.resetGame()
    }

    private fun setRules(firstTurn: Boolean) {
        if (firstTurn)
            binding.crapsTvRules.text = getString(
                R.string.blabla_enter_blabla,
                getString(R.string.you_win_if_get7or11),
                getString(R.string.you_lose_if_get2or3or12)
            )
        else
            binding.crapsTvRules.text = getString(
                R.string.blabla_enter_blabla,
                getString(R.string.you_win_if_target_score),
                getString(R.string.you_lose_if_get7)
            )
    }

}