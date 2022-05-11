package com.example.boardgamestools.model.roomData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game_table WHERE gameName = :name" )
    suspend fun loadGame(name : String) : GameDataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGame(game : GameDataEntity)

}