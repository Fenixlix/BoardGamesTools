package com.example.boardgamestools.model.roomData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlayerEntity::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun getPlayerDAO(): PlayerDAO
}