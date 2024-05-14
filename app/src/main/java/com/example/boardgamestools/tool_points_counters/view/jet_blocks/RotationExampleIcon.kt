package com.example.boardgamestools.tool_points_counters.view.jet_blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp


@Composable
fun RotationExampleIcon(id: Int, orientation: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.height(70.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (id % 2 != 0) WallIllustration()
        Icon(
            modifier = Modifier
                .size(40.dp)
                .rotate(orientation.toFloat()),
            imageVector = Icons.Outlined.AccountBox,
            contentDescription = null
        )
        if (id % 2 == 0) WallIllustration()
    }
}

@Composable
fun WallIllustration() {
    Spacer(
        modifier = Modifier
            .width(2.dp)
            .height(40.dp)
            .background(color = MaterialTheme.colorScheme.primary)
    )
}