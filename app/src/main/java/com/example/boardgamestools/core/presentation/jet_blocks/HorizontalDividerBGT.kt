package com.example.boardgamestools.core.presentation.jet_blocks

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDividerBGT(modifier: Modifier = Modifier) {
    HorizontalDivider(modifier = modifier
        .padding(horizontal = 4.dp)
        .padding(vertical = 1.dp))
}