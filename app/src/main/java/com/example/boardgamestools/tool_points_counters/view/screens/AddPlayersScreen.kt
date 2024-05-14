package com.example.boardgamestools.tool_points_counters.view.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.R
import com.example.boardgamestools.core.data.model.CounterBGT
import com.example.boardgamestools.core.presentation.jet_blocks.ButtonBGT
import com.example.boardgamestools.core.presentation.jet_blocks.HorizontalDividerBGT
import com.example.boardgamestools.core.presentation.jet_blocks.IntTextField
import com.example.boardgamestools.tool_points_counters.model.PointsCountersPlayer
import com.example.boardgamestools.tool_points_counters.view.jet_blocks.CountersCreationBox
import com.example.boardgamestools.tool_points_counters.view.jet_blocks.RotationExampleIcon

private val counterItem = "" to 0

@Composable
fun AddPlayersScreen(
    modifier: Modifier = Modifier,
    addPlayer: (PointsCountersPlayer) -> Unit,
    closeScreen: () -> Unit
) {
    var id by remember { mutableIntStateOf(0) }
    val defaultName = stringResource(id = R.string.player_n, id + 1)
    var name by remember(key1 = id) { mutableStateOf(defaultName) }
    var orientation by remember { mutableIntStateOf(0) }
    val countersList = remember { mutableStateListOf(counterItem) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .border(width = 2.dp, color = MaterialTheme.colorScheme.secondary)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // PLAYER NAME
            OutlinedTextField(
                modifier = Modifier.weight(0.5f),
                value = name,
                onValueChange = {
                    if (it.length <= 20 && !it.contains("(\\| | ¡)".toRegex())) {
                        name = it
                    }
                },
                label = { Text(text = stringResource(id = R.string.player_name)) })

            // ORIENTATION
            IntTextField(
                modifier = Modifier.weight(0.35f),
                intValue = orientation,
                label = R.string.orientation,
                onValueChange = { orientation = it },
                isError = orientation !in 0..360,
                supportText = R.string.degrees_range
            )

            RotationExampleIcon(
                id = id,
                orientation = orientation,
                modifier = Modifier.weight(0.15f)
            )

        }

        CountersCreationBox(
            modifier = Modifier.weight(1f),
            countersList = countersList,
            onNameChange = {
                countersList[it.first] = countersList[it.first].copy(first = it.second)
            },
            onPointsChange = {
                countersList[it.first] = countersList[it.first].copy(second = it.second)
            },
            onAddCounterClick = { countersList.add(counterItem) },
            onDeleteCounterClick = { countersList.removeAt(countersList.lastIndex) })

        HorizontalDividerBGT()

        ButtonBGT(btnText = R.string.add_btn) {
            val playerName = name.ifEmpty { defaultName }
            addPlayer(
                PointsCountersPlayer(
                    name = playerName,
                    id = id,
                    counterList = countersList.mapIndexed { index, pair ->
                        CounterBGT(
                            owner = playerName,
                            ownerId = id,
                            id = index,
                            name = pair.first,
                            points = pair.second
                        )
                    },
                    orientation = orientation
                )
            )
            orientation = 0
            id++
        }

        HorizontalDividerBGT(modifier = Modifier.padding(vertical = 4.dp))

        ButtonBGT(enabled = id > 0, btnText = R.string.game_start_btn) {
            closeScreen()
        }
    }
}