package com.example.boardgamestools.model.roomData

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game_table WHERE gameName = :name" )
    fun loadGame(name : String) : Flow<List<GameDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGame(game : GameDataEntity) : Long

}