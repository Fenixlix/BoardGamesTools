package com.example.boardgamestools.tool_coin_toss.view.jet_blocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.R

@Composable
fun GoldCoin(
    headOrTail: Boolean,
    modifier: Modifier = Modifier,
    clickEnable: Boolean = true,
    size: Int = 200,
    onClick: () -> Unit
) {
    Image(
        modifier = modifier
            .size(size.dp)
            .clip(shape = CircleShape)
            .clickable(enabled = clickEnable) { onClick() },
        painter = painterResource(
            id = if (headOrTail) {
                R.drawable.o_coin
            } else R.drawable.x_coin
        ),
        contentDescription = stringResource(
            id = if (headOrTail) R.string.o_coin else R.string.x_coin
        )
    )
}