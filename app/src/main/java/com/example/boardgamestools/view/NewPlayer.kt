package com.example.boardgamestools.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgamestools.R
import com.example.boardgamestools.databinding.ActivityNewPlayerBinding
import com.example.boardgamestools.model.GameApplication
import com.example.boardgamestools.model.IntentTags
import com.example.boardgamestools.model.listViewComponents.PlayerListAdapter
import com.example.boardgamestools.model.listViewComponents.RecyclerClickInterface
import com.example.boardgamestools.model.roomData.PlayerEntity
import com.example.boardgamestools.viewmodel.PlayerViewModel
import com.example.boardgamestools.viewmodel.PlayerViewModelFactory

class NewPlayer : AppCompatActivity() , RecyclerClickInterface {
    private lateinit var binding : ActivityNewPlayerBinding

    private val playerViewModel : PlayerViewModel by viewModels{
        PlayerViewModelFactory((application as GameApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playerList : ArrayList<PlayerEntity> = arrayListOf()
        val adapter = PlayerListAdapter(this)
        binding.rvExistingPlayers.adapter = adapter
        binding.rvExistingPlayers.layoutManager = LinearLayoutManager(this)

        var counter = 1

        playerViewModel.allPlayers.observe(this, Observer{
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

        binding.addButton.setOnClickListener {
            val name = if (binding.etPlayerName.text.isNullOrEmpty()){
                    "Player #${counter+1}"
                }else binding.etPlayerName.text.toString()

            val score = if (binding.etPlayerScore.text.isNullOrEmpty()){
                    0
                }else binding.etPlayerScore.text.toString().toInt()

            playerList.add(PlayerEntity(id = 0,name = name,score = score))
            playerViewModel.insert(playerList[counter-1])
            counter++
        }

        binding.startGameButton.setOnClickListener {
            finish()
        }
    }

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

    override fun onClick(position: Int) {
        val intent = Intent(this, ModifyPlayer::class.java)
        intent.putExtra(IntentTags.PLAYER.toStr, position)
        startActivity(intent)
        Toast.makeText(this, "Funcionara", Toast.LENGTH_LONG).show()
    }
}

