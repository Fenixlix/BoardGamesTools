package com.example.boardgamestools.core.presentation.jet_blocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PointsModTool(
    counterPoints: Int,
    modifier: Modifier = Modifier,
    plusOrMinusClick: (Int) -> Unit
) {
    var points by remember { mutableIntStateOf(0) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButtonBGT(
            enabler = true,
            icon = Icons.Filled.KeyboardArrowDown
        ) {
            plusOrMinusClick(counterPoints - points)
        }
        IntTextField(
            modifier = Modifier
                .padding(8.dp)
                .width(120.dp),
            intValue = points, label = null,
            onValueChange = { points = it })
        IconButtonBGT(
            enabler = true,
            icon = Icons.Filled.KeyboardArrowUp
        ) {
            plusOrMinusClick(counterPoints + points)
        }
    }
}