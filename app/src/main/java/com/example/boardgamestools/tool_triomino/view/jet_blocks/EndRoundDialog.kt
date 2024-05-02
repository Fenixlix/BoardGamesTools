package com.example.boardgamestools.tool_triomino.view.jet_blocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.boardgamestools.R
import com.example.boardgamestools.core.presentation.jet_blocks.ButtonBGT
import com.example.boardgamestools.core.presentation.jet_blocks.DialogColumn
import com.example.boardgamestools.core.presentation.jet_blocks.IconButtonBGT

@Composable
fun EndRoundDialog(
    show: Boolean,
    round: Int,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onFinishRoundClick: (Int) -> Unit
) {
    if (show) {
        var bonusPoint by remember {
            mutableStateOf("")
        }
        val addBtnEnabler by remember {
            derivedStateOf { bonusPoint.isNotBlank() }
        }
        var total: Int? by remember {
            mutableStateOf(null)
        }
        val areExtraPoints by remember {
            derivedStateOf { total != null }
        }

        Dialog(onDismissRequest = { onDismiss() }) {
            DialogColumn(modifier = modifier, height = 350.dp) {
                // Title
                Text(
                    text = stringResource(id = R.string.round_n_end_phase, round),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
                // Total extra points
                OutlinedTextField(
                    value = if (total == null) "" else total.toString(),
                    onValueChange = {},
                    label = { Text(text = stringResource(id = R.string.total_extra_points_tf)) },
                    supportingText = { if (!areExtraPoints)Text(text = stringResource(id = R.string.add_extra_points_tf)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 4.dp),
                    readOnly = true,
                    isError = !areExtraPoints
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Extra points
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .width(160.dp),
                        value = bonusPoint,
                        onValueChange = { bonusPoint = it },
                        label = { Text(text = stringResource(id = R.string.extra_points_tf)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    // Add Button
                    IconButtonBGT(
                        enabler = addBtnEnabler,
                        icon = Icons.Filled.Add,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        total = if (total == null) {
                            bonusPoint.toInt()
                        } else {
                            total!! + bonusPoint.toInt()
                        }
                        bonusPoint = ""
                    }
                }
                // Finish round btn
                ButtonBGT(
                    enabled = areExtraPoints,
                    btnText = R.string.finish_round_btn,
                    onClick = { onFinishRoundClick(total!!) })
            }
        }
    }
}