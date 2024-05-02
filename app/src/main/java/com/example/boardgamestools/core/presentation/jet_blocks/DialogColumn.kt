package com.example.boardgamestools.core.presentation.jet_blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DialogColumn(
    modifier: Modifier = Modifier,
    height: Dp = 300.dp,
    width: Dp = 400.dp,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .size(height = height, width = width)
            .background(
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.8f),
                shape = dialogShape
            )
            .border(
                color = MaterialTheme.colorScheme.tertiary,
                width = 4.dp,
                shape = dialogShape
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

val dialogShape = CutCornerShape(topStart = 20.dp, bottomEnd = 20.dp)