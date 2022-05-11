package com.example.boardgamestools.model.repository

import androidx.annotation.WorkerThread
import com.example.boardgamestools.model.roomData.PlayerDAO
import com.example.boardgamestools.model.roomData.PlayerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@WorkerThread
class PlayerRepository @Inject constructor(
    private val playerDAO: PlayerDAO
    ) {

    val allPlayers : Flow<List<PlayerEntity>> = playerDAO.getAll()

    suspend fun deleteAll(){
        playerDAO.deleteAll()
    }

    suspend fun insertNewPlayer(player : PlayerEntity){
        playerDAO.insertNewPlayer(player)
    }

    suspend fun deletePlayer(player: PlayerEntity){
        playerDAO.deletePlayer(player)
    }

    suspend fun updatePlayer(player: PlayerEntity){
        playerDAO.updateScore(player)
    }

    suspend fun resetScores(players : List<PlayerEntity>){
        playerDAO.resetScore(players)
    }

}