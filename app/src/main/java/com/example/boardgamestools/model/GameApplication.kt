package com.example.boardgamestools.model

import android.app.Application
import com.example.boardgamestools.model.roomData.PlayerRepository
import com.example.boardgamestools.model.roomData.RoomDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GameApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val dataBase by lazy{ RoomDB.getDataBase(this, applicationScope)}
    val repository by lazy{PlayerRepository(dataBase.playerDAO())}
}