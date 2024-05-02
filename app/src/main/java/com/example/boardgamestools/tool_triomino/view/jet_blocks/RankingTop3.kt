package com.example.boardgamestools.tool_triomino.view.jet_blocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.core.data.roomData.TriominoPlayerEntity
import com.example.boardgamestools.tool_triomino.model.TriominoRanks

@Composable
fun RankingTop3(players: List<TriominoPlayerEntity>) {
    val top1 = mutableListOf<TriominoPlayerEntity>()
    val top2 = mutableListOf<TriominoPlayerEntity>()
    val top3 = mutableListOf<TriominoPlayerEntity>()
    players.forEach {
        when (it.rank) {
            0 -> top1.add(it)
            1 -> top2.add(it)
            2 -> top3.add(it)
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top 1
        PlayersRankRow(ranks = TriominoRanks.FIRS, players = top1)
        // Top 2 and 3
        Row {
            PlayersRankRow(
                modifier = Modifier.weight(1f),
                ranks = TriominoRanks.SECOND,
                players = top2
            )
            PlayersRankRow(
                modifier = Modifier.weight(1f),
                ranks = TriominoRanks.THIRD,
                players = top3
            )
        }
    }
}

@Composable
private fun PlayersRankRow(
    ranks: TriominoRanks,
    players: List<TriominoPlayerEntity>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (ranks == TriominoRanks.FIRS) Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .size(
                    when (ranks) {
                        TriominoRanks.FIRS -> 100.dp
                        TriominoRanks.SECOND -> 80.dp
                        else -> 70.dp
                    }
                )
                .padding(horizontal = 2.dp),
            painter = painterResource(id = ranks.positionGraf),
            contentDescription = null
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .padding(start = 4.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(25f)
                )
                .padding(start = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(players) {
                PlayerData(name = it.name, score = it.score)
            }
        }
    }
}

@Composable
private fun PlayerData(name: String, score: Int) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = score.toString(),
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}