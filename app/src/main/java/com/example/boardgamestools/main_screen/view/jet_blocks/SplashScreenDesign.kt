package com.example.boardgamestools.main_screen.view.jet_blocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.boardgamestools.R

@Composable
fun SplashScreenDesign() {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
                .padding(30.dp),
            painter = painterResource(id = R.drawable.splash_screen_icon),
            contentDescription = null
        )
    }
}