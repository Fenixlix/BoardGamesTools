package com.example.boardgamestools.tool_triomino.view.jet_blocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.R
import com.example.boardgamestools.core.presentation.jet_blocks.ButtonBGT

@Composable
fun PlayersControls(
    gameEnabler: Boolean,
    modifier: Modifier = Modifier,
    onPassClick: () -> Unit,
    onFinishClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Pass Turn
        ButtonBGT(
            enabled = gameEnabler,
            btnText = R.string.pass_turn_btn,
            onClick = { onPassClick() })

        Spacer(modifier = Modifier.width(8.dp))

        // End Round
        ButtonBGT(
            enabled = gameEnabler,
            btnText = R.string.finish_round_btn,
            onClick = { onFinishClick() })
    }
}