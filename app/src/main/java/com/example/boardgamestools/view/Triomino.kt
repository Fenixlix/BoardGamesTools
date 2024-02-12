package com.example.boardgamestools.view

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgamestools.R
import com.example.boardgamestools.databinding.ActivityTriominoBinding
import com.example.boardgamestools.model.playerListComponents.PlayerListAdapter
import com.example.boardgamestools.model.roomData.GameDataEntity
import com.example.boardgamestools.model.roomData.PlayerEntity
import com.example.boardgamestools.model.utilities.*
import com.example.boardgamestools.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Triomino : AppCompatActivity(), ListClickInterface {

    // ----- binding & viewModel ----- //
    private lateinit var binding: ActivityTriominoBinding
    private val gameViewModel: GameViewModel by viewModels()

    // ----- Game related variables ----- //
    private var totalPlayers: List<PlayerEntity> = listOf()
    private var round = 0
    private var turn = 1
    private var firstPlaceScore = 0
    private var secondPlaceScore = 0
    private var thirdPlaceScore = 0

    // ----- On Create Method ----- //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTriominoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ----- Recycler view configurations ----- //
        val adapter = PlayerListAdapter(this)
        binding.rvTriominoPlayers.adapter = adapter
        binding.rvTriominoPlayers.layoutManager = LinearLayoutManager(this)

        // ----- View model configurations -----
        gameViewModel.allPlayers.observe(this) {
            adapter.submitList(it)
            totalPlayers = it
            if (round == 0) {
                startGame(totalPlayers)
                binding.tvRound.text = getString(R.string.round, ++round)
            } else {
                val player = totalPlayers[turn - 1]
                updatePlayer(player)
            }
        }

        // ----- Spinners configurations -----
        binding.spPunishPoints.adapter = ArrayAdapter(
            this,
            R.layout.custom_dropdown_item_layout,
            resources.getStringArray(R.array.sp_penalties_options)
        )

        binding.spTurnPoints.adapter = ArrayAdapter(
            this,
            R.layout.custom_dropdown_item_layout,
            resources.getStringArray(R.array.sp_turn_options)
        )

        binding.spComboPoints.adapter = ArrayAdapter(
            this,
            R.layout.custom_dropdown_item_layout,
            resources.getStringArray(R.array.sp_bonus_options)
        )

        // ----- Game buttons configurations ----- //
        /* Finish the round and selects the next player to start
         * Also refresh the active player screen */
        binding.btnFinishRound.setOnClickListener {
            restPiecesScoreMessage()
        }
        /*  Take the values assigned to the various spinners and add them to the score
         *  of the active player , then update the database, the turn and the screen */
        binding.btnPassTurn.setOnClickListener {
            endTurn()
            nextTurn()
        }
        // This function ends the activity and return to the main activity
        binding.btnEndgame.setOnClickListener {
            finish()
        }
    }

    // ----- Game Menu configurations ----- //
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.game_utilities_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.game_menu_save_ico -> {
                val gameData = GameDataEntity(
                    gameName = "Triomino",
                    round = round,
                    payerTurn = turn,
                    gameRanking = "$firstPlaceScore|$secondPlaceScore|$thirdPlaceScore",
                    extraData = ""
                )
                gameViewModel.saveGame(gameData)
                Toast.makeText(this, getString(R.string.game_saved), Toast.LENGTH_SHORT).show()
            }
            R.id.game_menu_load_ico -> {
                val gameData = gameViewModel.loadGame("Triomino")
                lifecycleScope.launch {
                    repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                        gameData.collect {
                            if (it.isNotEmpty()) {
                                round = it[0].round
                                turn = it[0].payerTurn
                                val ranking = it[0].gameRanking.split("|")
                                firstPlaceScore = ranking[0].toInt()
                                secondPlaceScore = ranking[1].toInt()
                                thirdPlaceScore = ranking[2].toInt()
                                val player = totalPlayers[turn - 1]
                                updatePlayer(player)
                                binding.tvRound.text = getString(R.string.round, round)
                                Toast.makeText(
                                    applicationContext,
                                    getString(R.string.game_loaded),
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    getString(R.string.no_game_saved),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // ----- THE GAME FUNCTIONS ----- //
    // Initialize the game screen layout with a random selected player
    private fun startGame(players: List<PlayerEntity>) {
        if (players.isNotEmpty()) {
            if (players.size > 1) {
                turn = (1..totalPlayers.size).random()
            }
            val player = players[turn - 1]
            updatePlayer(player)
        }
    }

    // Refresh the data of the active player in the screen
    private fun updatePlayer(player: PlayerEntity) {
        binding.tvCurrentPlayerName.text = player.name
        binding.tvCurrentPlayerScore.text = player.score.toString()
        val positionGraf = TriominoRanks.getPosition(
            firstPlaceScore,
            secondPlaceScore,
            thirdPlaceScore,
            player.score
        ).positionGraf
        binding.ivRanking.setImageResource(positionGraf)
    }

    // Finish the turn for the actual player, reset the spinners and update the scores
    private fun endTurn(extraScore: Int = 0, nextTurn: Int = (turn - 1)) {
        val negativePoints = binding.spPunishPoints.selectedItem.toString().toInt()
        val positivePoints = binding.spTurnPoints.selectedItem.toString().toInt()
        val bonusPoints = binding.spComboPoints.selectedItem.toString().toInt()

        val newScore =
            totalPlayers[nextTurn].score + positivePoints + bonusPoints - negativePoints + extraScore
        updateRanking(newScore)
        gameViewModel.updateScore(totalPlayers[nextTurn].copy(score = newScore))

        binding.spPunishPoints.setSelection(0)
        binding.spTurnPoints.setSelection(0)
        binding.spComboPoints.setSelection(0)
    }

    // Update the ranking values for the firs, second, third, and no rank player
    private fun updateRanking(score: Int) {
        when {
            score >= firstPlaceScore -> {
                firstPlaceScore = score
            }
            score >= secondPlaceScore -> {
                secondPlaceScore = score
            }
            score >= thirdPlaceScore -> {
                thirdPlaceScore = score
            }
        }
    }

    // Move the turn to the next player to play
    private fun nextTurn() {
        turn++
        if (turn > totalPlayers.size) {
            turn = 1
        }
    }

    // Select the lowest score player to be the first player to play in the next round
    private fun nextRoundFirstTurn(roundFinisher: Int) {
        var smallestScore = firstPlaceScore
        totalPlayers.forEach { player ->
            updateRanking(player.score)
            if (player.score <= smallestScore && player.id != roundFinisher + 1) {
                smallestScore = player.score
                turn = totalPlayers.indexOf(player) + 1
            }
        }
    }

    // Open an alert dialog for the residual points of the different players and start the new round
    private fun restPiecesScoreMessage() {
        val extraPoints: ArrayList<Int> = ArrayList()
        var total = 0
        val titleText = getString(R.string.round_end_phase, round)
        var message = getString(R.string.residual_points)

        val alertDialogBuilder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this)
            .inflate(
                R.layout.triomino_round_end_phase,
                findViewById(R.id.triomino_ad_container)
            )
        alertDialogBuilder.setView(view)

        val tvTitle = view.findViewById<TextView>(R.id.tv_alertD_Title)
        val tvMessage = view.findViewById<TextView>(R.id.tv_alertD_Message)
        val etPoints = view.findViewById<EditText>(R.id.et_alertD_Puntaje)
        val btnAdd = view.findViewById<Button>(R.id.btn_alertD_Add)
        val btnFinishRound = view.findViewById<Button>(R.id.btn_alertD_FinishRound)

        tvTitle.text = titleText
        tvMessage.text = message
        val alertDialog = alertDialogBuilder.create()
        etPoints.hint = getString(R.string.players_extra_points)

        btnAdd.setOnClickListener {
            if (etPoints.text.isNotEmpty()) {
                message += "${etPoints.text} | "
                tvMessage.text = message
                extraPoints.add(etPoints.text.toString().toInt())
                etPoints.text.clear()
            } else {
                Toast.makeText(this, getString(R.string.no_extra_points), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btnFinishRound.setOnClickListener {
            if (extraPoints.isNotEmpty()) {
                extraPoints.forEach {
                    total += it
                }
                val finishRoundPlayerTurn = turn - 1
                nextRoundFirstTurn(finishRoundPlayerTurn)
                endTurn(total, finishRoundPlayerTurn)
                binding.tvRound.text = getString(R.string.round, ++round)
                Toast.makeText(this, getString(R.string.round_End), Toast.LENGTH_LONG).show()
                alertDialog.dismiss()
            } else {
                Toast.makeText(this, getString(R.string.no_extra_points), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        if (alertDialog.window != null) {
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(0))
        }
        alertDialog.show()

    }

    // ----- onClick method requested by the ListClickInterface ----- //
    override fun onClick(position: Int) {
        val intent = Intent(this, ModifyPlayer::class.java)
        intent.putExtra(IntentTags.PLAYER.toStr, position)
        startActivity(intent)
    }
}