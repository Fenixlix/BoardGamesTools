package com.example.boardgamestools.tool_coin_toss.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.boardgamestools.tool_coin_toss.model.CoinTossEvents
import com.example.boardgamestools.tool_coin_toss.model.CoinTossState
import com.example.boardgamestools.tool_coin_toss.view.jet_blocks.CoinCountersBar
import com.example.boardgamestools.tool_coin_toss.view.jet_blocks.GoldCoin
import com.example.boardgamestools.tool_coin_toss.viewmodel.CoinTossViewModel

@Composable
fun CoinTossScreenRoot(viewModel: CoinTossViewModel = hiltViewModel()) {
    CoinTossScreen(screenState = viewModel.state.collectAsState(), onEvent = viewModel::onEvent)
}

@Composable
private fun CoinTossScreen(screenState: State<CoinTossState>, onEvent: (CoinTossEvents) -> Unit) {
    val state = screenState.value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CoinCountersBar(
            counters = state.counterList,
            selectedBox = state.selectedBox,
            onBoxClick = { onEvent(CoinTossEvents.SelectBox(it)) },
            onAddClick = { onEvent(CoinTossEvents.AddCounterBox) }
        )
        Spacer(modifier = Modifier.weight(1f))
        GoldCoin(headOrTail = state.headOrTail) {
            onEvent(CoinTossEvents.TossCoin)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}