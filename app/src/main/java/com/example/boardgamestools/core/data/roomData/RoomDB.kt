package com.example.boardgamestools.core.data.roomData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TriominoPlayerEntity::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun getTriominoPlayerDAO(): TriominoPlayerDAO
}