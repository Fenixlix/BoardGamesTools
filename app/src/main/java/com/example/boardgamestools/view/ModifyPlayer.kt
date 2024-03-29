package com.example.boardgamestools.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.boardgamestools.R
import com.example.boardgamestools.databinding.ActivityModifyPlayerBinding
import com.example.boardgamestools.model.utilities.IntentTags
import com.example.boardgamestools.model.roomData.PlayerEntity
import com.example.boardgamestools.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModifyPlayer : AppCompatActivity() {

    private lateinit var binding: ActivityModifyPlayerBinding
    private val gameViewModel: GameViewModel by viewModels()

    private lateinit var toModifyPlayer: PlayerEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(IntentTags.PLAYER.toStr, 1)

        gameViewModel.allPlayers.observe(this){
            toModifyPlayer = it[id]
            binding.modPlayerEtPlayerName.setText(toModifyPlayer.name)
            binding.modPlayerEtPlayerScore.setText(toModifyPlayer.score.toString())
        }

        binding.modPlayerBtn.setOnClickListener {
            val name = binding.modPlayerEtPlayerName.text.toString()
            val score = binding.modPlayerEtPlayerScore.text.toString().toInt()

            if (name.isNotEmpty()) {
                gameViewModel.updateScore(
                    toModifyPlayer.copy(
                        name = name,
                        score = score
                    )
                )
            }
            finish()
        }

        binding.modPlayerCancelBtn.setOnClickListener {
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_player_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteMenuIco -> {
                val confirmationScreenBuilder = AlertDialog.Builder(this)
                confirmationScreenBuilder.setTitle(getString(R.string.delete_dialog_title))
                confirmationScreenBuilder.setMessage(getString(R.string.confirm_player_delete,toModifyPlayer.name))
                confirmationScreenBuilder.setPositiveButton(getString(R.string.delete_dialog_yes)) { _, _ ->
                    gameViewModel.allPlayers.removeObserver{}
                    gameViewModel.deletePlayer(toModifyPlayer)
                    finish()
                }
                confirmationScreenBuilder.setNegativeButton(getString(R.string.delete_dialog_no)) { _, _ -> }
                confirmationScreenBuilder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}