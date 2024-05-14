package com.example.boardgamestools.tool_points_counters.view.jet_blocks

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.core.data.model.CounterBGT
import com.example.boardgamestools.core.presentation.jet_blocks.HorizontalDividerBGT
import com.example.boardgamestools.tool_points_counters.model.PointsCountersPlayer

@Composable
fun CounterPlayerDisplay(
    player: PointsCountersPlayer,
    modifier: Modifier = Modifier,
    onCounterClick: (CounterBGT, Int) -> Unit
) {
    Column(
        modifier = modifier
            .rotate(player.orientation.toFloat())
            .size(170.dp)
            .border(
                width = 4.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = player.name)
        HorizontalDividerBGT()
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(player.counterList) {
                CounterButton(name = it.name, points = it.points) {
                    onCounterClick(it, player.orientation)
                }
            }
        }
    }
}