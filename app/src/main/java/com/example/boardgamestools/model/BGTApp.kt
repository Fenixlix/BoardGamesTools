package com.example.boardgamestools.model

import android.app.Application
import com.example.boardgamestools.model.roomData.PlayerRepository
import com.example.boardgamestools.model.roomData.RoomDB
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class BGTApp : Application()