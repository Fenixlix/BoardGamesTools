package com.example.boardgamestools.tool_points_counters.model

import com.example.boardgamestools.core.data.model.CounterBGT

data class PointsCountersPlayer(
    val id: Int,
    val name : String,
    val counterList : List<CounterBGT>,
    val orientation : Int = 0
)
