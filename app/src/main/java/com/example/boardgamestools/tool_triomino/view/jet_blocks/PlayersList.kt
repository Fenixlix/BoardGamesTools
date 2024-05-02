package com.example.boardgamestools.tool_triomino.view.jet_blocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.core.data.roomData.TriominoPlayerEntity
import com.example.boardgamestools.tool_triomino.model.TriominoRanks

@Composable
fun PlayersList(
    currentTurn: Int?,
    players: List<TriominoPlayerEntity>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(players) { index, player ->
            PlayerData(currentTurn, index, player = player)
        }
    }
}

@Composable
private fun PlayerData(currentTurn: Int?, index: Int, player: TriominoPlayerEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp)
            .background(
                color = if (currentTurn != null && index == currentTurn) {
                    MaterialTheme.colorScheme.tertiary
                } else MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                shape = RoundedCornerShape(25f)
            )
            .padding(horizontal = 8.dp)
            .padding(vertical = if (currentTurn != null && index == currentTurn) 16.dp else 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = player.name,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            modifier = Modifier.padding(8.dp),
            text = player.score.toString(),
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = TriominoRanks.entries[player.rank].positionGraf),
            contentDescription = null
        )
    }
}

