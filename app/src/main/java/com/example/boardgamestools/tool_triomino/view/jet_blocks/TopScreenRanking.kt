package com.example.boardgamestools.tool_triomino.view.jet_blocks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boardgamestools.R
import com.example.boardgamestools.core.data.roomData.TriominoPlayerEntity

@Composable
fun TopScreenRanking(round : Int, players : List<TriominoPlayerEntity>, modifier : Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth().padding(8.dp)) {
        RankingTop3(players = players)
        Text(
            text = stringResource(id = R.string.round, round),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}