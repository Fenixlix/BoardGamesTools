package com.example.boardgamestools.model.roomData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myGames")
data class Game(@PrimaryKey @ColumnInfo(name = "gameName") val name:String ,
                @ColumnInfo(name = "rules") var myGameRules :String,
                @ColumnInfo(name = "image") val gameLogo : Int)
