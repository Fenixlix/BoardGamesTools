package com.example.boardgamestools.model.roomData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players_table")
data class PlayerEntity(
        @PrimaryKey(autoGenerate = true) val id : Int,
        @ColumnInfo(name = "playerName") val name: String,
        @ColumnInfo(name = "playerScore") val score: Int = 0
)
