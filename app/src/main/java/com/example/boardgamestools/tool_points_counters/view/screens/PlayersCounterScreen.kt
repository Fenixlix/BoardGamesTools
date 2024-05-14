package com.example.boardgamestools.tool_points_counters.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.core.data.model.CounterBGT
import com.example.boardgamestools.tool_points_counters.model.PointsCountersPlayer
import com.example.boardgamestools.tool_points_counters.view.jet_blocks.CounterPlayerDisplay

@Composable
fun PlayersCountersScreen(
    players: List<PointsCountersPlayer>,
    modifier: Modifier = Modifier,
    onCounterClick: (CounterBGT, Int) -> Unit
) {
    val shareModifier = modifier
        .fillMaxSize()
        .padding(4.dp)

    if (players.size <= 2) {
        Column(
            modifier = shareModifier,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            players.forEach { player ->
                CounterPlayerDisplay(player = player) { counter, rotation ->
                    onCounterClick(counter, rotation)
                }
            }
        }
    } else {
        LazyVerticalGrid(
            modifier = shareModifier,
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            items(players) { player ->
                CounterPlayerDisplay(player = player) { counter, rotation ->
                    onCounterClick(counter, rotation)
                }
            }
        }
    }
}