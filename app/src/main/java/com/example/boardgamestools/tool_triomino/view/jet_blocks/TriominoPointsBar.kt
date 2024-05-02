package com.example.boardgamestools.tool_triomino.view.jet_blocks

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.R

@Composable
fun TriominoPointBar(
    modifier: Modifier = Modifier,
    score: Int?,
    penalty: Int?,
    bonus: Int?,
    onScoreSelected: (Int) -> Unit,
    onBonusSelected: (Int) -> Unit,
    onPenaltySelected: (Int) -> Unit
) {
    val scorePoints = remember { List(16) { it } }
    val penaltyPoints = remember { List(5) { it * 5 } }
    val bonusPoints = remember { listOf(0, 30, 40, 50, 80, 120) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        //Score
        Spinner(
            modifier = modifier.weight(1f),
            textResource = R.string.turn_points_drop_box,
            currentSelection = score,
            values = scorePoints,
            onSelect = { onScoreSelected(it) })
        //Bonus
        Spinner(
            modifier = modifier.weight(1f),
            textResource = R.string.bonus_points_drop_box,
            currentSelection = bonus,
            values = bonusPoints,
            onSelect = { onBonusSelected(it) })
        //Penalty
        Spinner(
            modifier = modifier.weight(1f),
            textResource = R.string.penalty_points_drop_box,
            currentSelection = penalty,
            values = penaltyPoints,
            onSelect = { onPenaltySelected(it) })
    }
}

@Composable
private fun Spinner(
    @StringRes textResource: Int,
    currentSelection: Int?,
    values: List<Int>,
    modifier: Modifier = Modifier,
    onSelect: (Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.padding(4.dp), contentAlignment = Alignment.Center) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false })
        {
            values.forEach {
                DropdownMenuItem(text = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it.toString(),
                            textAlign = TextAlign.Center
                        )
                    }
                }, onClick = {
                    onSelect(it)
                    expanded = false
                })
            }
        }
        OutlinedTextField(
            value = currentSelection?.toString() ?: "",
            onValueChange = {},
            label = {
                Text(
                    text = stringResource(id = textResource),
                    fontWeight = FontWeight.Medium
                )
            },
            readOnly = true,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(50.dp)
            .clickable { expanded = true })
    }
}
