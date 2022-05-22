package com.example.boardgamestools.model.utilities

import com.example.boardgamestools.R
import com.example.boardgamestools.view.Craps
import com.example.boardgamestools.view.Players
import com.example.boardgamestools.view.Triomino

object Games {
    val listOfGames = listOf(
        Game(Players().gameName, R.drawable.players, Players::class.java),
        Game(Triomino().gameName, R.drawable.triomino_logo, Triomino::class.java),
        Game(Craps().gameName, R.drawable.craps_logo, Craps::class.java)
        // Add more games here
    )

}

