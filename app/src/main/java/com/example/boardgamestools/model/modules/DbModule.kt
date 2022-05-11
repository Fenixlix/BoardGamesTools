package com.example.boardgamestools.model.modules

import android.content.Context
import androidx.room.Room
import com.example.boardgamestools.model.roomData.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    private const val DATA_BASE_NAME = "bgt_db"

    @Singleton
    @Provides
    fun providesRoomDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RoomDB::class.java, DATA_BASE_NAME).build()

    @Singleton
    @Provides
    fun providesPlayerDao(db: RoomDB) = db.getPlayerDAO()

    @Singleton
    @Provides
    fun providesGameDao(db: RoomDB) = db.getGameDao()

}