package com.example.boardgamestools.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.boardgamestools.databinding.ActivityMainBinding
import com.example.boardgamestools.model.gameListComponents.GamesListAdapter
import com.example.boardgamestools.model.utilities.IntentTags
import com.example.boardgamestools.model.utilities.Games
import com.example.boardgamestools.model.utilities.ListClickInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , ListClickInterface{

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = GamesListAdapter(this)
        adapter.submitList(Games.listOfGames)
        binding.rvGameRList.adapter = adapter
        binding.rvGameRList.layoutManager = GridLayoutManager(this, 2)

    }

    override fun onClick(position: Int) {
        val intent = when (position){
            2-> Intent(this, Games.listOfGames[position].direction)
            3-> Intent(this, Games.listOfGames[position].direction)
            else -> Intent(this, Games.listOfGames[0].direction)
        }
        if ( position >0 ) intent.putExtra(IntentTags.GAME.toStr,position)
        startActivity(intent)
    }

}