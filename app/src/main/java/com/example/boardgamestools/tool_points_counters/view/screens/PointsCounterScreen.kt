package com.example.boardgamestools.tool_points_counters.view.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.boardgamestools.core.presentation.jet_blocks.ContinueOrNewGameDialog
import com.example.boardgamestools.core.presentation.jet_blocks.PointsEditDialog
import com.example.boardgamestools.tool_points_counters.model.PointsCountersEvent
import com.example.boardgamestools.tool_points_counters.model.PointsCountersState
import com.example.boardgamestools.tool_points_counters.viewmodel.PointsCountersViewModel

@Composable
fun PointsCounterScreenRoot(viewModel: PointsCountersViewModel = hiltViewModel()) {
    PointsCountersScreen(state = viewModel.state, onEvent = viewModel::onEvent)
}

@Composable
private fun PointsCountersScreen(
    state: PointsCountersState,
    onEvent: (PointsCountersEvent) -> Unit
) {
    ContinueOrNewGameDialog(
        show = state.showContinueOrNewGameDialog,
        newGame = {
            if (it) {
                onEvent(PointsCountersEvent.NewGame)
            } else {
                onEvent(PointsCountersEvent.CloseContinueOrNewGameDialog)
            }
        })


    PointsEditDialog(
        show = state.showPointsEditDialog,
        counter = state.counterVessel,
        rotation = state.rotationVessel,
        onDismiss = { onEvent(PointsCountersEvent.ClosePointsEditDialog) },
        updateCount = { onEvent(PointsCountersEvent.UpdateCounter(it)) })

    AnimatedVisibility(state.showAddPlayersScreen) {
        AddPlayersScreen(
            addPlayer = { onEvent(PointsCountersEvent.AddPlayer(it)) },
            closeScreen = { onEvent(PointsCountersEvent.CloseAddPlayersScreen) })
    }
    AnimatedVisibility(visible = state.showAddPlayersScreen.not()) {
        PlayersCountersScreen(
            players = state.playersList,
            onCounterClick = { counter, rotation ->
                onEvent(PointsCountersEvent.OpenPointsEditDialog(counter, rotation))
            })
    }

}