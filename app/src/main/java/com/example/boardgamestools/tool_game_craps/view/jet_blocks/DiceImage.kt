package com.example.boardgamestools.tool_game_craps.view.jet_blocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.R

@Composable
fun DiceImage(value : Int, modifier : Modifier = Modifier) {

    val image = when(value){
        1 -> R.drawable.d1
        2 -> R.drawable.d2
        3 -> R.drawable.d3
        4 -> R.drawable.d4
        5 -> R.drawable.d5
        6 -> R.drawable.d6
        else -> R.drawable.craps_logo
    }

    Image(modifier = modifier.size(160.dp).padding(16.dp) ,painter = painterResource(id = image), contentDescription = "")
}