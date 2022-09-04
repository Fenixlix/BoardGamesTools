package com.example.boardgamestools.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.boardgamestools.databinding.ActivityManyCountersBinding

import kotlinx.coroutines.flow.flow

class ManyCounters : AppCompatActivity() {
    lateinit var binding : ActivityManyCountersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManyCountersBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}