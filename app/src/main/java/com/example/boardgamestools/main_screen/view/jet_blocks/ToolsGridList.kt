package com.example.boardgamestools.main_screen.view.jet_blocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.core.data.model.GameTools

@Composable
fun ToolsGridList(
    toolList: List<GameTools>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(toolList) { tool ->
            ToolCard(
                toolTitle = stringResource(id = tool.toolData.name),
                contentDescription = stringResource(id = tool.toolData.toolDescription),
                painter = painterResource(id = tool.toolData.icon)
            ) {
                // To use as a direction for the nav controller
                onClick(tool.name)
            }
        }

    }
}