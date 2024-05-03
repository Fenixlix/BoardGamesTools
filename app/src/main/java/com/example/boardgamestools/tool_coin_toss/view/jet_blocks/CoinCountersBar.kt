package com.example.boardgamestools.tool_coin_toss.view.jet_blocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.core.presentation.jet_blocks.IconButtonBGT
import com.example.boardgamestools.tool_coin_toss.model.CoinCounter

@Composable
fun CoinCountersBar(
    counters: List<CoinCounter>,
    selectedBox: Int,
    modifier: Modifier = Modifier,
    onBoxClick: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(counters) {
            CoinCounterBox(coinCounter = it, selected = it.position == selectedBox) {
                onBoxClick(it.position)
            }
        }
        item {
            IconButtonBGT(
                modifier = Modifier.padding(8.dp),
                enabler = true,
                icon = Icons.Filled.Add
            ) {
                onAddClick()
            }
        }
    }
}