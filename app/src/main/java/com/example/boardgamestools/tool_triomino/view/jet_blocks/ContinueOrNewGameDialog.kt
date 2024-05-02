package com.example.boardgamestools.tool_triomino.view.jet_blocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.boardgamestools.R
import com.example.boardgamestools.core.presentation.jet_blocks.DialogColumn

@Composable
fun ContinueOrNewGameDialog(
    show: Boolean,
    modifier: Modifier = Modifier,
    newGame: (Boolean) -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { newGame(false) }) {
            DialogColumn(modifier = modifier) {
                // Title
                Text(
                    text = stringResource(id = R.string.continue_or_new_game),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp
                )
                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Continue
                    Button(modifier = Modifier.padding(4.dp), onClick = { newGame(false) }) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = stringResource(id = R.string.continue_game_btn),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                    }
                    // New Game
                    Button(modifier = Modifier.padding(4.dp), onClick = { newGame(true) }) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = stringResource(id = R.string.new_game_btn),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}