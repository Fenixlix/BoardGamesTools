package com.example.boardgamestools.core.data.roomData

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PointsPlayerDao {

    @Query("SELECT * FROM points_counters_table")
    fun getAll() : Flow<List<PointsPlayerEntity>>

    @Query("DELETE FROM points_counters_table")
    suspend fun deleteAll()

    @Upsert
    suspend fun upsertPlayer(player: PointsPlayerEntity)

}