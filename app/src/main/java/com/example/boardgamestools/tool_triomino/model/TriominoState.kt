package com.example.boardgamestools.tool_triomino.model

import com.example.boardgamestools.core.data.roomData.TriominoPlayerEntity

data class TriominoState(
    val turn: Int = 0,
    val round: Int = 1,
    val ranking: TriominoRanking = TriominoRanking(),
    val scorePoints: Int? = null,
    val bonusPoints: Int? = null,
    val penaltyPoints: Int? = null,
    val endRoundDialog: Boolean = false,
    val addPlayerDialog: Boolean = false,
    val continueOrNewGameDialog: Boolean = true,
    val players: List<TriominoPlayerEntity> = emptyList()
)
