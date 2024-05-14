package com.example.boardgamestools.tool_points_counters.view.jet_blocks

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.core.presentation.jet_blocks.IconButtonBGT

@Composable
fun CountersCreationBox(
    countersList: List<Pair<String, Int>>,
    modifier: Modifier = Modifier,
    onNameChange: (Pair<Int, String>) -> Unit,
    onPointsChange: (Pair<Int, Int>) -> Unit,
    onAddCounterClick: () -> Unit,
    onDeleteCounterClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(countersList) { index, counter ->
            CounterDataItem(
                name = counter.first,
                points = counter.second,
                onNameChange = { onNameChange(index to it) },
                onPointsChange = { onPointsChange(index to it) })
        }

        item {
            HorizontalDivider(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButtonBGT(enabler = true, icon = Icons.Filled.Add) {
                    onAddCounterClick()
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButtonBGT(enabler = countersList.isNotEmpty(), icon = Icons.Filled.Delete) {
                    onDeleteCounterClick()
                }
            }
        }
    }
}