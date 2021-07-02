package com.example.covidmonitor.di.modules

import android.content.Context
import androidx.room.Room
import com.example.covidmonitor.mvp.model.cache.ContinentCache
import com.example.covidmonitor.mvp.model.cache.RoomContinentsCache
import com.example.covidmonitor.mvp.model.entity.room.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, Database.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun continentsCache(database: Database): ContinentCache = RoomContinentsCache(database)
}