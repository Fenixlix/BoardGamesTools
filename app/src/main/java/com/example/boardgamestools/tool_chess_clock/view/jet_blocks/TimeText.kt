package com.example.boardgamestools.tool_chess_clock.view.jet_blocks

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimeText(time: Int, rotation: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 4.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(25f)
            ), contentAlignment = Alignment.Center
    ) {
        Text(
            text = time.toTime(),
            modifier = Modifier
                .fillMaxWidth()
                .rotate(rotation),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            fontSize = 80.sp
        )
    }
}

// Extension function to get a time format from an Int with the total seconds count
private fun Int.toTime(): String {
    return "${this / 60}:${this % 60}"
}