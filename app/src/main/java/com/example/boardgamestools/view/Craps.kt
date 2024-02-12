package com.example.boardgamestools.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.boardgamestools.R
import com.example.boardgamestools.simpleGames.CrapsGame
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Craps : AppCompatActivity() {

    // Creating the view component to be initialize later
    private lateinit var btn : Button
    private lateinit var winLoseTxt : TextView
    private lateinit var targetScoreTxt : TextView
    private lateinit var dice1Graf : ImageView
    private lateinit var dice2Graf : ImageView
    private lateinit var rulesTxt : TextView

    // Craps game variables
    @Inject lateinit var crapsGame : CrapsGame
    private var turnScore = 0
    private var targetScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craps)

        // Accessing the view component without data binding
        btn = findViewById(R.id.craps_btn_toss_dices)
        winLoseTxt = findViewById(R.id.craps_tv_win_lose)
        targetScoreTxt = findViewById(R.id.craps_tv_Score)
        dice1Graf = findViewById(R.id.craps_dice1)
        dice2Graf = findViewById(R.id.craps_dice2)
        rulesTxt = findViewById(R.id.craps_tv_rules)

        setRules(true)

        btn.setOnClickListener {
            // todo: learn to make animations based in the time of the button is been press
            if (crapsGame.gameState() != 0){
                resetGame()
                setRules(true)
            }
            else {
                setDices(crapsGame.takeTurn())
                updateStatus(crapsGame.gameState())
            }
        }
    }
    private fun updateStatus(status : Int){
        winLoseTxt.text = when(status){
            1 -> {
                clearData()
                getString(R.string.player_win)
            }
            0 -> {
                if (targetScore == 0) {
                    setRules(false)
                    targetScore = turnScore
                    targetScoreTxt.text = getString(R.string.target_score, targetScore)
                }
                getString(R.string.game_continue)
            }
            -1 ->{
                clearData()
                getString(R.string.player_lose)
            }
            else -> getString(R.string.game_start)
        }
    }

    private fun setDices(dices : IntArray ){
        val (dice1, dice2) = dices
        turnScore = dice1 + dice2
        dice1Graf.setImageResource(getDiceImg(dice1))
        dice2Graf.setImageResource(getDiceImg(dice2))
    }

    private fun getDiceImg(point : Int) : Int{
        return when(point){
            1-> R.drawable.d1
            2-> R.drawable.d2
            3-> R.drawable.d3
            4-> R.drawable.d4
            5-> R.drawable.d5
            6-> R.drawable.d6
            else -> R.drawable.craps_logo
        }
    }

    private fun clearData(){
        targetScore = 0
        turnScore = 0
        targetScoreTxt.text = getString(R.string.target_score,0)
        btn.text = getString(R.string.reset_game)
    }

    private fun resetGame(){
        updateStatus(2)
        btn.text = getString(R.string.take_turn)
        crapsGame.resetGame()
    }

    private fun setRules(firstTurn: Boolean){
        if (firstTurn)
            rulesTxt.text = getString(R.string.blabla_enter_blabla,
                getString(R.string.you_win_if_get7or11),
                getString(R.string.you_lose_if_get2or3or12))
        else
            rulesTxt.text = getString(R.string.blabla_enter_blabla,
                getString(R.string.you_win_if_target_score),
                getString(R.string.you_lose_if_get7))
    }

}