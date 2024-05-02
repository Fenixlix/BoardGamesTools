package com.example.boardgamestools.core.presentation.jet_blocks

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonBGT(
    @StringRes btnText: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textSize : Int = 20,
    onClick: () -> Unit,
) {
    Button(enabled = enabled, onClick = { onClick() }, modifier = modifier) {
        Text(
            text = stringResource(id = btnText),
            modifier = Modifier.padding(4.dp),
            fontWeight = FontWeight.Medium,
            fontSize = textSize.sp,
            textAlign = TextAlign.Center
        )
    }
}