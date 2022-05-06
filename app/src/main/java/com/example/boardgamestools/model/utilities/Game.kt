package com.example.boardgamestools.model.utilities

data class Game(
    val name: String,
    val icon: Int,
    val direction: Class<*>
)
