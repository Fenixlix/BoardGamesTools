package com.example.boardgamestools.tool_triomino.model

import com.example.boardgamestools.core.data.roomData.TriominoPlayerEntity

sealed interface TriominoEvent {
    data object NewGame : TriominoEvent
    data object ContinueGame : TriominoEvent
    data object TogglePlayersDialog : TriominoEvent
    data object ToggleEndRoundDialog : TriominoEvent
    data object PassTurn : TriominoEvent
    data class FinishRound(val extra : Int) : TriominoEvent
    data class ScoreSelected(val score :Int) : TriominoEvent
    data class BonusSelected(val bonus :Int) : TriominoEvent
    data class PenaltySelected(val penalty :Int) : TriominoEvent
    data class AddPlayer(val player: TriominoPlayerEntity) : TriominoEvent
}