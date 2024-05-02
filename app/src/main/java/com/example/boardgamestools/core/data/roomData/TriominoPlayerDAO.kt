package com.example.boardgamestools.core.data.roomData

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TriominoPlayerDAO {

    @Query("SELECT * FROM triomino_players_table")
    fun getAll(): Flow<List<TriominoPlayerEntity>>

    @Query("SELECT * FROM triomino_players_table WHERE id = :requestedId")
    suspend fun getPlayer(requestedId : Int) : TriominoPlayerEntity

    @Query("DELETE FROM triomino_players_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewPlayer(newPlayer : TriominoPlayerEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun updateScore(existingPlayer: TriominoPlayerEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun resetScore(players : List<TriominoPlayerEntity>){
        // Todo: with the score set to 0 the rank may be set to GG as well
        players.forEach {
            updateScore(it.copy(score = 0))
        }
    }

    @Delete
    suspend fun deletePlayer(player: TriominoPlayerEntity)

}