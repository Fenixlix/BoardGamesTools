package com.example.boardgamestools.core.presentation.jet_blocks

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.boardgamestools.core.data.model.CounterBGT

@Composable
fun PointsEditDialog(
    show: Boolean,
    counter: CounterBGT?,
    modifier: Modifier = Modifier,
    rotation: Int? = null,
    onDismiss: () -> Unit,
    updateCount: (CounterBGT) -> Unit
) {
    if (show && counter != null) {
        Dialog(onDismissRequest = { onDismiss() }) {
            DialogColumn(
                modifier = modifier
                    .rotate(rotation?.toFloat() ?: 0f),
                width = 300.dp
            ) {
                Text(
                    text = counter.owner,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
                HorizontalDividerBGT()
                OutlinedTextField(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    value = counter.points.toString(),
                    onValueChange = { },
                    label = { Text(text = counter.name) },
                    readOnly = true,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center, fontSize = 24.sp
                    )
                )
                PointsModTool(counterPoints = counter.points) { updateCount(counter.copy(points = it)) }
            }
        }
    }
}