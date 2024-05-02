package com.example.boardgamestools.core.data.model

import com.example.boardgamestools.R

enum class GameTools(val toolData: GameTool) {
    TRIOMINO(
        GameTool(
            name = R.string.triomino,
            toolDescription = R.string.triomino_td,
            icon = R.drawable.triomino_logo
        )
    ),
    CRAPS(
        GameTool(
            name = R.string.craps,
            toolDescription = R.string.craps_td,
            icon = R.drawable.craps_logo
        )
    ),
    CHESS_CLOCK(
        GameTool(
            name = R.string.chess_clock,
            toolDescription = R.string.chess_clock_td,
            icon = R.drawable.chessclockico
        )
    ),
    COIN_TOSS(
        GameTool(
            name = R.string.coin_toss,
            toolDescription = R.string.coin_toss_td,
            icon = R.drawable.triomino_logo // todo: create and import and change
        )
    ),
    POINT_COUNTERS(
        GameTool(
            name = R.string.point_counters,
            toolDescription = R.string.point_counters_td,
            icon = R.drawable.triomino_logo // todo: create and import and change
        )
    ),
    CHARACTERS(
        GameTool(
            name = R.string.characters,
            toolDescription = R.string.characters_td,
            icon = R.drawable.triomino_logo // todo: create and import and change
        )
    )
}