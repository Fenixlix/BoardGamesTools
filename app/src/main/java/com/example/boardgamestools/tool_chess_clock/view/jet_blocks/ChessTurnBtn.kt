package com.example.boardgamestools.tool_chess_clock.view.jet_blocks

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.R

@Composable
fun ChessTurnBtn(pressed: Boolean, modifier: Modifier = Modifier, onCLick: () -> Unit) {
    ElevatedButton(
        modifier = modifier.padding(4.dp),
        onClick = { onCLick() },
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = if (pressed) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.secondary
        ),
        content = {
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.sand_clock_icon),
                contentDescription = null
            )
        },
        elevation = if (pressed) {
            ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 0.dp
            )
        } else ButtonDefaults.elevatedButtonElevation()
    )
}