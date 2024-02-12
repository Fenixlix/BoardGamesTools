package com.example.boardgamestools.model.utilities.chess_clock

data class ChessState(
    val p1Turn: Boolean? = null,
    val p1Time: Int = 0,
    val p2Time: Int = 0
)
