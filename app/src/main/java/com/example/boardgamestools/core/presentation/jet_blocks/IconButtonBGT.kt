package com.example.boardgamestools.core.presentation.jet_blocks

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonBGT(
    enabler: Boolean,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(
        enabled = enabler,
        colors = IconButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color.Gray
        ),
        modifier = modifier.size(50.dp),
        onClick = {
            onClick()
        }) {
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}