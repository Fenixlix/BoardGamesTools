package com.example.boardgamestools.tool_coin_toss.view.jet_blocks

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.R
import com.example.boardgamestools.tool_coin_toss.model.CoinCounter

@Composable
fun CoinCounterBox(
    coinCounter: CoinCounter,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .size(100.dp)
            .border(
                width = if (selected) 4.dp else 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.coin_n, coinCounter.position + 1),
                textAlign = TextAlign.Center
            )
            CoinCount(oOrX = true, count = coinCounter.numOfHeads)
            CoinCount(oOrX = false, count = coinCounter.numOfTails)
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() })
    }
}

@Composable
private fun CoinCount(oOrX: Boolean, count: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        GoldCoin(headOrTail = oOrX, size = 30, clickEnable = false) {}
        Text(text = count.toString(), textAlign = TextAlign.End)
    }
}