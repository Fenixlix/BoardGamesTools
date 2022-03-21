package com.example.boardgamestools.model.roomData

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class PlayerRepository(private val playerDAO: PlayerDAO) {

    val allPlayers : Flow<List<PlayerEntity>> = playerDAO.getAll()

    suspend fun getPlayer(id: Int) : PlayerEntity{
        return playerDAO.getPlayer(id)
    }

    suspend fun deleteAll(){
        playerDAO.deleteAll()
    }

    @WorkerThread
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