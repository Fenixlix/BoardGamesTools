package com.example.boardgamestools.model.roomData

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDAO {

    @Query("SELECT * FROM players_table")
    fun getAll(): Flow<List<PlayerEntity>>

    @Query("SELECT * FROM players_table WHERE id = :requestedId")
    suspend fun getPlayer(requestedId : Int) : PlayerEntity

    @Query("DELETE FROM players_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewPlayer(newPlayer : PlayerEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun updateScore(existingPlayer: PlayerEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun resetScore(players : List<PlayerEntity>){
        players.forEach {
            updateScore(it.copy(score = 0))
        }
    }

    @Delete
    suspend fun deletePlayer(player: PlayerEntity)

}