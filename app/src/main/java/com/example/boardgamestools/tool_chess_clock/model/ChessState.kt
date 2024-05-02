package com.example.boardgamestools.tool_chess_clock.model

data class ChessState(
    val p1Turn: Boolean? = null,
    val p1Time: Int = 0,
    val p2Time: Int = 0,
    val gameIsOn: Boolean? = null
)
