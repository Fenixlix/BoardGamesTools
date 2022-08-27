package com.example.boardgamestools.model.roomData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class GameDataEntity(
    @PrimaryKey(autoGenerate = false) val gameName : String,
    @ColumnInfo val round : Int = 0,
    @ColumnInfo val payerTurn : Int = 0,
    @ColumnInfo val gameRanking : String = "",
    @ColumnInfo val extraData : String = ""
)
