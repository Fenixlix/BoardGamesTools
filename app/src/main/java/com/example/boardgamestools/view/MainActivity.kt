package com.example.boardgamestools.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.boardgamestools.databinding.ActivityMainBinding
import com.example.boardgamestools.model.IntentTags
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTriomino.setOnClickListener {
            val intent = Intent(this, Players::class.java)
            intent.putExtra(IntentTags.GAME.toStr,IntentTags.TRIOMINO.toStr)
            startActivity(intent)
        }

        binding.btnNewPlayer.setOnClickListener {
            val intent = Intent(this, Players::class.java)
            startActivity(intent)
        }

    }

}