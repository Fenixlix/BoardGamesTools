package com.example.boardgamestools.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.boardgamestools.databinding.ActivityMainBinding
import com.example.boardgamestools.model.IntentTags
import com.example.boardgamestools.model.recyclerComponents.GamesListAdapter
import com.example.boardgamestools.model.tempData

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTriomino.setOnClickListener {
            val intent = Intent(this, Triomino::class.java)
            intent.putExtra(IntentTags.GAME.toStr,IntentTags.TRIOMINO.toStr)
            startActivity(intent)
        }

        binding.btnNewPlayer.setOnClickListener {
            val intent = Intent(this, NewPlayer::class.java)
            startActivity(intent)
        }

    }

}