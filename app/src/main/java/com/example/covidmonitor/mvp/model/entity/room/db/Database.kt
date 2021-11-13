package com.example.covidmonitor.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import com.example.covidmonitor.mvp.model.entity.room.RoomContinent
import com.example.covidmonitor.mvp.model.entity.room.RoomCountry
import com.example.covidmonitor.mvp.model.entity.room.dao.ContinentDao
import com.example.covidmonitor.mvp.model.entity.room.dao.CountryDao

@androidx.room.Database(entities = [RoomContinent::class, RoomCountry::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract val continentDao: ContinentDao
    abstract val countryDao: CountryDao
    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
    }
}