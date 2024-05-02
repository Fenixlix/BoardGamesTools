package com.example.boardgamestools.main_screen.view.screens

import androidx.compose.runtime.Composable
import com.example.boardgamestools.core.data.model.GameTools
import com.example.boardgamestools.main_screen.view.jet_blocks.ToolsGridList

@Composable
fun HomeScreen(
    onToolSelect : (String) -> Unit
){
    ToolsGridList(toolList = GameTools.entries) {
        onToolSelect(it)
    }
}