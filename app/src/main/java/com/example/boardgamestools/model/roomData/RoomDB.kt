package com.example.boardgamestools.model.roomData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [PlayerEntity::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun playerDAO(): PlayerDAO

    private class RoomDBCallback(private val scope : CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    initializeDatabase(database.playerDAO())
                }
            }
        }

        suspend fun initializeDatabase(playerDAO: PlayerDAO) {
            playerDAO.deleteAll()
            playerDAO.insertNewPlayer(PlayerEntity(0,"Player #1",0))
        }
    }

    companion object {

        @Volatile
        private var INSTANCE : RoomDB? = null

        fun getDataBase(context: Context, scope: CoroutineScope) : RoomDB{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDB::class.java,
                        "players_table"
                    ).addCallback(RoomDBCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}