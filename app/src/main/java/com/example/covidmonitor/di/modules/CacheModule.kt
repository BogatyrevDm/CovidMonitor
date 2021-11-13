package com.example.covidmonitor.di.modules

import com.example.covidmonitor.mvp.model.cache.ContinentCache
import com.example.covidmonitor.mvp.model.cache.CountriesCache
import com.example.covidmonitor.mvp.model.cache.RoomContinentsCache
import com.example.covidmonitor.mvp.model.cache.RoomCountriesCache
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface CacheModule {
    @Singleton
    @Binds
    fun bindContinentsCache(roomContinentsCache:RoomContinentsCache): ContinentCache

    @Singleton
    @Binds
    fun bindCountriesCache(roomCountriesCache:RoomCountriesCache): CountriesCache
}