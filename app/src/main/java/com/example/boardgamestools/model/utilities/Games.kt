package com.example.boardgamestools.model.utilities

import com.example.boardgamestools.R
import com.example.boardgamestools.view.ChessClock
import com.example.boardgamestools.view.Craps
import com.example.boardgamestools.view.Players
import com.example.boardgamestools.view.Triomino

object Games {
    val listOfGames = listOf(
        Game("Players", R.drawable.players_logo, Players::class.java),
        Game("Triomino", R.drawable.triomino_logo, Triomino::class.java),
        Game("Craps", R.drawable.craps_logo, Craps::class.java),
        Game("ChessClock", R.drawable.chessclockico, ChessClock::class.java)
        // Add more games here
    )
}

