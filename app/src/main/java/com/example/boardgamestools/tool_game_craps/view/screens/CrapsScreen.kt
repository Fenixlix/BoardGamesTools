package com.example.boardgamestools.tool_game_craps.view.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.boardgamestools.R
import com.example.boardgamestools.core.presentation.jet_blocks.ButtonBGT
import com.example.boardgamestools.tool_game_craps.model.CrapsState
import com.example.boardgamestools.tool_game_craps.view.jet_blocks.DiceImage
import com.example.boardgamestools.tool_game_craps.viewmodel.CrapsViewModel

@Composable
fun CrapsScreenRoot(
    viewModel: CrapsViewModel = hiltViewModel()
) {
    CrapsScreen(
        state = viewModel.state,
        onClick = viewModel::takeTurn
    )
}

@Composable
private fun CrapsScreen(state: CrapsState, onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Game Title
        CustomText(
            resourceText = state.title,
            size = 34.sp
        )

        // Game rules
        CustomText(
            resourceText = state.gameRules,
            weight = FontWeight.Medium
        )

        // Dice images
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(25f)
                )
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DiceImage(value = state.currentDices.first)
            DiceImage(value = state.currentDices.second)
        }

        // Target game score
        CustomText(
            resourceText = R.string.target_score,
            extraData = state.targetScore,
            weight = FontWeight.Medium
        )

        // Button
        ButtonBGT(
            btnText = state.btnMessage,
            textSize = 30,
            modifier = Modifier.padding(8.dp),
            onClick = { onClick() })
    }
}

@Composable
private fun CustomText(
    resourceText: Int,
    extraData: Int? = null,
    weight: FontWeight = FontWeight.Bold,
    size: TextUnit = 24.sp
) {
    Text(
        text = if (extraData != null) stringResource(
            id = resourceText,
            extraData
        ) else stringResource(id = resourceText),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        textAlign = TextAlign.Center,
        fontWeight = weight,
        fontSize = size
    )
}