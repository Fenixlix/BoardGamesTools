package com.example.boardgamestools.tool_points_counters.view.jet_blocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.R

@Composable
fun CounterDataItem(
    name: String,
    points: Int,
    modifier: Modifier = Modifier,
    onNameChange: (String) -> Unit,
    onPointsChange: (Int) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(0.6f),
            value = name,
            onValueChange = { onNameChange(it) },
            label = { Text(text = stringResource(id = R.string.name)) },
            supportingText = {
                if (name.isNotEmpty()) Text(
                    text = stringResource(id = R.string.chars_limit, name.length, 12),
                    textAlign = TextAlign.End
                )
            })
        OutlinedTextField(
            modifier = modifier.weight(0.4f),
            value = points.toString(),
            onValueChange = { onPointsChange(if (it.isEmpty()) 0 else it.toInt()) },
            label = { Text(text = stringResource(id = R.string.points)) },
            supportingText = {
                if (name.isNotEmpty())
                    Text(
                        text = stringResource(
                            id = R.string.chars_limit,
                            points.toString().length,
                            8
                        ),
                        textAlign = TextAlign.End
                    )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )
    }
}