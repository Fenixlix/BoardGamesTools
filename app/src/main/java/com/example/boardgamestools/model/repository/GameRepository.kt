package com.example.boardgamestools.model.repository

import androidx.annotation.WorkerThread
import com.example.boardgamestools.model.roomData.GameDao
import com.example.boardgamestools.model.roomData.GameDataEntity
import javax.inject.Inject

@WorkerThread
class GameRepository @Inject constructor(
    private val gameDao: GameDao
) {

    fun loadGame(name : String) = gameDao.loadGame(name)

    suspend fun saveGame(game : GameDataEntity) = gameDao.saveGame(game)
}