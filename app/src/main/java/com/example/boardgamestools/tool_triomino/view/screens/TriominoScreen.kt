package com.example.boardgamestools.tool_triomino.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.boardgamestools.tool_triomino.model.TriominoEvent
import com.example.boardgamestools.tool_triomino.model.TriominoState
import com.example.boardgamestools.tool_triomino.view.jet_blocks.ContinueOrNewGameDialog
import com.example.boardgamestools.tool_triomino.view.jet_blocks.EmptyPlayersList
import com.example.boardgamestools.tool_triomino.view.jet_blocks.EndRoundDialog
import com.example.boardgamestools.tool_triomino.view.jet_blocks.PlayersControls
import com.example.boardgamestools.tool_triomino.view.jet_blocks.PlayersDialog
import com.example.boardgamestools.tool_triomino.view.jet_blocks.PlayersList
import com.example.boardgamestools.tool_triomino.view.jet_blocks.TopScreenRanking
import com.example.boardgamestools.tool_triomino.view.jet_blocks.TriominoPointBar
import com.example.boardgamestools.tool_triomino.viewmodel.TriominoViewModel

@Composable
fun TriominoScreenRoot(
    viewModel: TriominoViewModel = hiltViewModel()
) {
    TriominoScreen(
        screenState = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun TriominoScreen(
    screenState: TriominoState,
    onEvent: (TriominoEvent) -> Unit
) {

    val state = screenState

    val sufficientPlayers by remember(key1 = state.players.size) {
        mutableStateOf(state.players.size > 1)
    }

    // Initial Dialog
    ContinueOrNewGameDialog(show = state.continueOrNewGameDialog) { newGame ->
        if (newGame) onEvent(TriominoEvent.NewGame) else onEvent(TriominoEvent.ContinueGame)
    }
    // Add Players Dialog
    PlayersDialog(
        show = state.addPlayerDialog,
        players = state.players,
        onDismiss = { onEvent(TriominoEvent.TogglePlayersDialog) },
        onAddPlayerClick = { onEvent(TriominoEvent.AddPlayer(it)) })
    // End Round Dialog
    EndRoundDialog(
        show = state.endRoundDialog,
        round = state.round,
        onDismiss = { onEvent(TriominoEvent.ToggleEndRoundDialog) },
        onFinishRoundClick = { onEvent(TriominoEvent.FinishRound(it)) })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        TopScreenRanking(round = state.round, players = state.players)
        TriominoPointBar(
            score = state.scorePoints,
            penalty = state.penaltyPoints,
            bonus = state.bonusPoints,
            onScoreSelected = { onEvent(TriominoEvent.ScoreSelected(it)) },
            onBonusSelected = { onEvent(TriominoEvent.BonusSelected(it)) },
            onPenaltySelected = { onEvent(TriominoEvent.PenaltySelected(it)) }
        )
        if (sufficientPlayers) {
            PlayersList(
                modifier = Modifier.weight(1f),
                currentTurn = state.turn,
                players = state.players
            )
        } else {
            EmptyPlayersList(
                modifier = Modifier.weight(1f),
                onClick = { onEvent(TriominoEvent.TogglePlayersDialog) })
        }
        PlayersControls(
            gameEnabler = sufficientPlayers,
            onPassClick = { onEvent(TriominoEvent.PassTurn) },
            onFinishClick = { onEvent(TriominoEvent.ToggleEndRoundDialog) })
    }
}