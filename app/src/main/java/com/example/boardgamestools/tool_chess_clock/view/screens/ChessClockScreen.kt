package com.example.boardgamestools.tool_chess_clock.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.boardgamestools.R
import com.example.boardgamestools.tool_chess_clock.model.ChessGameEvent
import com.example.boardgamestools.tool_chess_clock.model.ChessState
import com.example.boardgamestools.tool_chess_clock.view.jet_blocks.ChessTurnBtn
import com.example.boardgamestools.tool_chess_clock.view.jet_blocks.TimeText
import com.example.boardgamestools.tool_chess_clock.viewmodel.ChessClockViewModel

@Composable
fun ChessClockScreenRoot(
    viewModel: ChessClockViewModel = hiltViewModel<ChessClockViewModel>()
) {
    ChessClockScreen(
        state = viewModel.chessGameState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ChessClockScreen(
    state: ChessState,
    onEvent: (ChessGameEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //P1
        TimeText(time = state.p1Time, rotation = 180f, modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(contentAlignment = Alignment.Center){
                Button(modifier = Modifier.rotate(90f),onClick = {
                    when (state.gameIsOn) {
                        true -> onEvent(ChessGameEvent.Checkmate)
                        false -> onEvent(ChessGameEvent.GameReset)
                        null -> {}
                    }
                }) {
                    val message = when (state.gameIsOn) {
                        true -> stringResource(id = R.string.checkmate)
                        false -> stringResource(id = R.string.reset_game_btn)
                        null -> ""
                    }
                    if (message.isNotBlank()) {
                        Text(
                            text = message,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // P1 btn
                ChessTurnBtn(modifier = Modifier.rotate(180f), pressed = state.p1Turn?.not() ?: false) {
                    onEvent(ChessGameEvent.P2Turn)
                }
                // P2 btn
                ChessTurnBtn(pressed = state.p1Turn ?: false) {
                    onEvent(ChessGameEvent.P1Turn)
                }
            }
        }
        //P2
        TimeText(time = state.p2Time, rotation = 0f, modifier = Modifier.weight(1f))
    }
}