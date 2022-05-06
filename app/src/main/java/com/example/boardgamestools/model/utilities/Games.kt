package com.example.boardgamestools.model.utilities

import com.example.boardgamestools.R
import com.example.boardgamestools.view.Players
import com.example.boardgamestools.view.Triomino

object Games {
    val listOfGames = listOf(
        Game("Players", R.drawable.players, Players::class.java),
        Game("Triomino", R.drawable.triomino_logo, Triomino::class.java),
        Game("Triomino2", R.drawable.triomino_logo, Triomino::class.java),
        Game("Triomino3", R.drawable.triomino_logo, Triomino::class.java)
        // Add more games here
    )
}


