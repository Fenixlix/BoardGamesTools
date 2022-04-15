package com.example.boardgamestools.view

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer

import com.example.boardgamestools.R
import com.example.boardgamestools.databinding.ActivityModifyPlayerBinding
import com.example.boardgamestools.model.IntentTags
import com.example.boardgamestools.model.roomData.PlayerEntity
import com.example.boardgamestools.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModifyPlayer : AppCompatActivity() {

    private lateinit var binding : ActivityModifyPlayerBinding
    private val playerViewModel : PlayerViewModel  by viewModels()

    private lateinit var toModifyPlayer : PlayerEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playerViewModel.allPlayers.observe(this, Observer {
            val id = intent.getIntExtra(IntentTags.PLAYER.toStr,1)

            toModifyPlayer = playerViewModel.getPlayer(id)!!
            binding.modPlayerEtPlayerName.setText(toModifyPlayer.name)
            binding.modPlayerEtPlayerScore.setText(toModifyPlayer.score.toString())
        })

        binding.modPlayerBtn.setOnClickListener {
            val name = binding.modPlayerEtPlayerName.text.toString()
            val score = binding.modPlayerEtPlayerScore.text.toString().toInt()

            if (name.isNotEmpty()){
                playerViewModel.updateScore(toModifyPlayer.copy(
                    name = name,
                    score = score))
            }
            finish()
        }

        binding.modPlayerCancelBtn.setOnClickListener {
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_player_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.deleteMenuIco -> {
                val confirmationScreenBuilder = AlertDialog.Builder(this)
                //val confirmationScreenView = LayoutInflater.inflate(R.id.asfasf) para hacer con vista personalizada
                //confirmationScreenBuilder.setView(confirmationScreenView)
                confirmationScreenBuilder.setTitle("Delete?")
                confirmationScreenBuilder.setMessage("Are you sure to delete the player: ${toModifyPlayer.name}")
                confirmationScreenBuilder.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                    playerViewModel.deletePlayer(toModifyPlayer)
                    finish()
                })
                confirmationScreenBuilder.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->  })
                confirmationScreenBuilder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}