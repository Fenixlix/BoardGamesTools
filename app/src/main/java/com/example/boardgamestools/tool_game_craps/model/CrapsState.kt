package com.example.boardgamestools.tool_game_craps.model

import androidx.annotation.StringRes
import com.example.boardgamestools.R

data class CrapsState(
    val currentDices: Pair<Int, Int> = Pair(3, 5),
    val targetScore: Int = 0,
    @StringRes val title: Int = R.string.craps,
    @StringRes val gameRules: Int = R.string.craps_first_turn_rules,
    @StringRes val btnMessage: Int = R.string.game_start_btn
)
