package com.example.boardgamestools.tool_chess_clock.model

sealed interface ChessGameEvent {
    data object Checkmate : ChessGameEvent
    data object P1Turn : ChessGameEvent
    data object P2Turn : ChessGameEvent
    data object GameReset : ChessGameEvent
}
