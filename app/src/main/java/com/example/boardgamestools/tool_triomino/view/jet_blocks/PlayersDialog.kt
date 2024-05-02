package com.example.boardgamestools.tool_triomino.view.jet_blocks

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.boardgamestools.R
import com.example.boardgamestools.core.data.roomData.TriominoPlayerEntity
import com.example.boardgamestools.core.presentation.jet_blocks.DialogColumn
import com.example.boardgamestools.core.presentation.jet_blocks.IconButtonBGT

@Composable
fun PlayersDialog(
    show: Boolean,
    players: List<TriominoPlayerEntity>,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onAddPlayerClick: (TriominoPlayerEntity) -> Unit
) {
    if (show) {
        var name by remember {
            mutableStateOf("")
        }
        val btnEnabler by remember {
            derivedStateOf { name.isNotBlank() }
        }
        Dialog(onDismissRequest = { onDismiss() }) {
            DialogColumn(modifier = modifier) {
                // Player name
                OutlinedTextField(value = name, onValueChange = { name = it }, label = {
                    Text(text = stringResource(id = R.string.player_name))
                })
                // Add Button
                IconButtonBGT(enabler = btnEnabler, icon = Icons.Filled.Add) {
                    onAddPlayerClick(
                        TriominoPlayerEntity(
                            name = name,
                            id = 0,
                            score = 0,
                            rank = 3
                        )
                    )
                    name = ""
                }
                PlayersList(currentTurn = null, players = players)
            }
        }
    }
}