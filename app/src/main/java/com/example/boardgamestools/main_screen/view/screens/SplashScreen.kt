package com.example.boardgamestools.main_screen.view.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.boardgamestools.main_screen.view.jet_blocks.SplashScreenDesign
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (onFinish : () -> Unit) {
    SplashScreenDesign()
    LaunchedEffect(key1 = true){
        delay(200) // just to show the splash screen
        onFinish()
    }
}