package com.example.boardgamestools.model.roomData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlayerEntity::class, GameDataEntity::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun getGameDao(): GameDao
    abstract fun getPlayerDAO(): PlayerDAO
}