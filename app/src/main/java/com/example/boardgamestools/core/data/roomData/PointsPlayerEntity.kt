package com.example.boardgamestools.core.data.roomData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "points_counters_table")
data class PointsPlayerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "counters") val counters: String,
    @ColumnInfo(name = "orientation") val orientation: Int
)
