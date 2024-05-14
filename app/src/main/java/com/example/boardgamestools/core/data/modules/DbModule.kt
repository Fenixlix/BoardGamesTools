package com.example.boardgamestools.core.data.modules

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.boardgamestools.core.data.roomData.RoomDB
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
    fun providesPlayerDao(db: RoomDB) = db.getTriominoPlayerDAO()

    @Singleton
    @Provides
    fun providesPointsPlayerDao(db: RoomDB) = db.getPointsPlayerDao()

    private val Context.dataStoreBGT by preferencesDataStore(name = "bgt")

    @Singleton
    @Provides
    fun providesDataStore(@ApplicationContext context: Context) = context.dataStoreBGT
}