package com.example.boardgamestools.main_screen.view.jet_blocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToolCard(
    toolTitle: String,
    contentDescription: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .border(
                width = 4.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            ), onClick = { onClick() }) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painter, contentDescription = contentDescription)
            Text(
                text = toolTitle,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                        shape = CircleShape
                    )
                    .padding(8.dp),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}