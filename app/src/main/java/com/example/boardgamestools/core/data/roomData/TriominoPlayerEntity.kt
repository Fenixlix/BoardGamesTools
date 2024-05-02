package com.example.boardgamestools.core.data.roomData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "triomino_players_table")
data class TriominoPlayerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "playerName") val name: String,
    @ColumnInfo(name = "playerScore") val score: Int,
    @ColumnInfo(name = "rank") val rank : Int
)