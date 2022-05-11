package com.example.boardgamestools.model.roomData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class GameDataEntity(
    @PrimaryKey val gameName : String,
    val round : Int = 0,
    val payerTurn : Int = 0,
    val gameRanking : String = "",
    val extraData : String = ""
)
