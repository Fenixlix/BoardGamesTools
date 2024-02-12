package com.example.boardgamestools.model.utilities.chess_clock

sealed interface ChessGameEvent {
    object Checkmate : ChessGameEvent
    object P1Turn : ChessGameEvent
    object P2Turn : ChessGameEvent
    object GameReset : ChessGameEvent
}
