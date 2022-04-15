package com.example.boardgamestools.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgamestools.R
import com.example.boardgamestools.databinding.ActivityNewPlayerBinding
import com.example.boardgamestools.model.IntentTags
import com.example.boardgamestools.model.listViewComponents.PlayerListAdapter
import com.example.boardgamestools.model.listViewComponents.RecyclerClickInterface
import com.example.boardgamestools.model.roomData.PlayerEntity
import com.example.boardgamestools.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Players : AppCompatActivity() , RecyclerClickInterface {
    private lateinit var binding : ActivityNewPlayerBinding

    private val playerViewModel : PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gameToBePlayed : String? = null   // Contains the name of the game to be played

        // Modify the name of the Start Game Button
        if(intent.hasExtra(IntentTags.GAME.toStr)){
            gameToBePlayed = intent.getStringExtra(IntentTags.GAME.toStr)
            val startGameButtonName : String = if (gameToBePlayed!=null){
                "Start $gameToBePlayed"
            } else {
                "Home"
            }
            binding.startGameButton.text = startGameButtonName
        }

        // Recycler view components configuration
        val adapter = PlayerListAdapter(this)
        binding.rvExistingPlayers.adapter = adapter
        binding.rvExistingPlayers.layoutManager = LinearLayoutManager(this)

        // PlayerViewModel Observer of the player list
        playerViewModel.allPlayers.observe(this) {
            adapter.submitList(it)  // Update the Recycler view
        }

        // Add Button click listener, add to the db the new player
        binding.addButton.setOnClickListener {
            val name = if (binding.etPlayerName.text.isNullOrEmpty()){
                    "Player #${adapter.itemCount+1}"
                }else binding.etPlayerName.text.toString()

            val score = if (binding.etPlayerScore.text.isNullOrEmpty()){
                    0
                }else binding.etPlayerScore.text.toString().toInt()

            playerViewModel.insert(PlayerEntity(id = 0,name = name,score = score))
        }

        // Start Game Button on click configuration and the game selector
        binding.startGameButton.setOnClickListener {
            when {
                gameToBePlayed != null -> {
                    // ----- Game selector
                    when (gameToBePlayed){
                        IntentTags.TRIOMINO.toStr -> {
                            finish()
                            startActivity(Intent(this, Triomino::class.java))
                        }
                        else -> finish()
                    }
                }
                adapter.itemCount != 0 -> {
                    finish()
                }
                else -> Toast.makeText(this, "There is no player to play",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ----- Menu configurations ----- //
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_player_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.deleteMenuIco -> playerViewModel.deleteAll()

            R.id.resetMenuIco -> playerViewModel.allPlayers.value?.let {
                playerViewModel.resetScore(it)
            } ?: Toast.makeText(this,getString(R.string.no_player_to_reset),Toast.LENGTH_SHORT).show()

        }
        return super.onOptionsItemSelected(item)
    }

    // ----- onClick function requested by the recycler view click Interface ----- //
    override fun onClick(position: Int) {
        val intent = Intent(this, ModifyPlayer::class.java)
        intent.putExtra(IntentTags.PLAYER.toStr, position)
        startActivity(intent)
    }
}

