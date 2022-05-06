package com.example.boardgamestools.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgamestools.R
import com.example.boardgamestools.databinding.ActivityTriominoBinding
import com.example.boardgamestools.model.utilities.IntentTags
import com.example.boardgamestools.model.utilities.TriominoRanks
import com.example.boardgamestools.model.playerListComponents.PlayerListAdapter
import com.example.boardgamestools.model.utilities.ListClickInterface
import com.example.boardgamestools.model.roomData.PlayerEntity
import com.example.boardgamestools.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Triomino : AppCompatActivity() , ListClickInterface {
    private lateinit var binding : ActivityTriominoBinding

    private val playerViewModel : PlayerViewModel by viewModels()

    private lateinit var totalPlayers : List<PlayerEntity>

    private var firstPlaceScore = 0
    private var secondPlaceScore = 0
    private var thirdPlaceScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTriominoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ----- Recycler view configurations -----
        val adapter = PlayerListAdapter(this)
        binding.rvTriominoPlayers.adapter = adapter
        binding.rvTriominoPlayers.layoutManager = LinearLayoutManager(this)

        totalPlayers = listOf()

        // ----- View model configurations -----
        playerViewModel.allPlayers.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
                totalPlayers = it
                if (playerViewModel.round == 0){
                    startGame(totalPlayers)
                    binding.tvRound.text = getString(R.string.round,++playerViewModel.round)
                } else {
                    val player = totalPlayers[playerViewModel.turn-1]
                    updatePlayer(player)
                }
            }
        })

        // ----- Spinners configurations -----
        binding.spPunishPoints.adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.sp_penalties_options))

        binding.spTurnPoints.adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.sp_turn_options))

        binding.spComboPoints.adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.sp_bonus_options))

        // ----- Game buttons configurations -----
        /**
         * Finish the round and selects the next player to start
         * Also refresh the active player screen
         */
        binding.btnFinishRound.setOnClickListener {
            restPiecesScoreMessage()
        }

        /**
         *  Take the values assigned to the various spinners and add them to the score
         *  of the active player , then update the database, the turn and the screen
         */
        binding.btnPassTurn.setOnClickListener {
            endTurn()
            nextTurn()
        }

        /**
         * This function ends the activity and return to the main activity
         */
        binding.btnEndgame.setOnClickListener {
            finish()
        }
    }

    /** ////////////////////////////////////////////////////////////////////////////////////////////
     * THE GAME UTILITIES  */

    /**
     * Initialize the game screen layout with a random selected player
     */
    private fun startGame(players : List<PlayerEntity>){
        if (players.isNotEmpty()){
            if (players.size > 1){
                playerViewModel.turn = (1..totalPlayers.size).random()
            }
            val player = players[playerViewModel.turn-1]
            updatePlayer(player)
        }
    }

    /**
     * Refresh the data of the active player in the screen
     */
    private fun updatePlayer(player: PlayerEntity){
        binding.tvCurrentPlayerName.text = player.name
        binding.tvCurrentPlayerScore.text = player.score.toString()
        val positionGraf = TriominoRanks.getPosition(firstPlaceScore,secondPlaceScore,thirdPlaceScore,player.score).positionGraf
        binding.ivRanking.setImageResource(positionGraf)
    }

    private fun endTurn(extraScore :Int = 0, nextTurn : Int = (playerViewModel.turn-1)){
        val negativePoints = binding.spPunishPoints.selectedItem.toString().toInt()
        val positivePoints = binding.spTurnPoints.selectedItem.toString().toInt()
        val bonusPoints = binding.spComboPoints.selectedItem.toString().toInt()

        val newScore = totalPlayers[nextTurn].score  + positivePoints + bonusPoints - negativePoints + extraScore
        updateRanking(newScore)
        playerViewModel.updateScore(totalPlayers[nextTurn].copy(score = newScore))

        binding.spPunishPoints.setSelection(0)
        binding.spTurnPoints.setSelection(0)
        binding.spComboPoints.setSelection(0)
    }
    /**
     * update the ranking values for the firs, second, third, and no rank player.
     */
    private fun updateRanking(score : Int){
        when {
            score>= firstPlaceScore -> {
                firstPlaceScore = score
            }
            score>= secondPlaceScore -> {
                secondPlaceScore = score
            }
            score>= thirdPlaceScore -> {
                thirdPlaceScore = score
            }
        }
    }

    /**
     * move the turn to the next player to play
     */
    private fun nextTurn(){
        playerViewModel.turn ++
        if(playerViewModel.turn>totalPlayers.size){
            playerViewModel.turn = 1
        }
    }

    /**
     * Select the lowest score player to be the first player to play in the next round
     */
    private fun nextRoundFirstTurn(roundFinisher : Int){
        var smallestScore = firstPlaceScore
        totalPlayers.forEach { player ->
            updateRanking(player.score)
            if(player.score<=smallestScore && player.id != roundFinisher+1){
                smallestScore = player.score
                playerViewModel.turn = player.id
            }
        }
    }

    private fun restPiecesScoreMessage(){
        val extraPoints : ArrayList<Int> = ArrayList()
        var total = 0
        val titleText = "Round #${playerViewModel.round} end phase"
        var message = "Residual points: "

        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.triomino_round_end_phase,null)
        alertDialog.setView(view)

        val tv_Title = view.findViewById<TextView>(R.id.tv_alertD_Title)
        val tv_Message = view.findViewById<TextView>(R.id.tv_alertD_Message)
        val et_Points = view.findViewById<EditText>(R.id.et_alertD_Puntaje)
        val btn_Add = view.findViewById<Button>(R.id.btn_alertD_Add)
        val btn_FinishRound = view.findViewById<Button>(R.id.btn_alertD_FinishRound)

        tv_Title.text = titleText
        tv_Message.text = message
        alertDialog.create().show()

        btn_Add.setOnClickListener{
            if (et_Points.text.isNotEmpty()) {
                message += "${et_Points.text} | "
                tv_Message.text = message
                extraPoints.add(et_Points.text.toString().toInt())
                et_Points.text.clear()
            }
        }

        btn_FinishRound.setOnClickListener{
            if (extraPoints.isNotEmpty()) {
                extraPoints.forEach {
                    total += it
                }
                val finishRoundPlayerTurn = playerViewModel.turn - 1
                nextRoundFirstTurn(finishRoundPlayerTurn)
                endTurn(total, finishRoundPlayerTurn)
                binding.tvRound.text = getString(R.string.round, ++playerViewModel.round)
                Toast.makeText(this, "Round Finish", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Click outside to continue", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(position: Int) {
        val intent = Intent(this, ModifyPlayer::class.java)
        intent.putExtra(IntentTags.PLAYER.toStr, position)
        startActivity(intent)
    }
}