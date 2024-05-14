package com.example.boardgamestools.core.presentation.jet_blocks

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun IntTextField(
    intValue: Int,
    @StringRes label: Int?,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    @StringRes supportText: Int? = null,
    isError: Boolean = false,
    onValueChange: (Int) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = intValue.toString(),
        onValueChange = { onValueChange(if (it.isEmpty()) 0 else it.toInt()) },
        label = { if (label != null) Text(text = stringResource(id = label)) },
        readOnly = readOnly,
        supportingText = { if (supportText != null) Text(text = stringResource(id = supportText)) },
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        )
    )
}