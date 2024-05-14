package com.example.boardgamestools.tool_points_counters.model

import com.example.boardgamestools.core.data.model.CounterBGT

sealed interface PointsCountersEvent {
    data object NewGame : PointsCountersEvent
    data object CloseContinueOrNewGameDialog : PointsCountersEvent
    data class OpenPointsEditDialog(val counter: CounterBGT, val rotation : Int) : PointsCountersEvent
    data object ClosePointsEditDialog : PointsCountersEvent
    data class AddPlayer(val player: PointsCountersPlayer) : PointsCountersEvent
    data object CloseAddPlayersScreen : PointsCountersEvent
    data class UpdateCounter(val newCounter: CounterBGT) : PointsCountersEvent
}