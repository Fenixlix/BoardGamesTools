package com.example.boardgamestools.tool_points_counters.model

import com.example.boardgamestools.core.data.model.CounterBGT

data class PointsCountersState(
    val showContinueOrNewGameDialog: Boolean = true,
    val showPointsEditDialog: Boolean = false,
    val showAddPlayersScreen: Boolean = false,
    val counterVessel: CounterBGT? = null,
    val rotationVessel : Int? = null,
    val playersList: List<PointsCountersPlayer> = emptyList()
)
