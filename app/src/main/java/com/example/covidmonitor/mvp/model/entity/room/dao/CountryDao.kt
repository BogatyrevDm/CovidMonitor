package com.example.covidmonitor.mvp.model.entity.room.dao

import androidx.room.*
import com.example.covidmonitor.mvp.model.entity.room.RoomCountry
import io.reactivex.rxjava3.core.Completable

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(continents: List<RoomCountry>): Completable

    @Update
    fun update(continent: RoomCountry)

    @Delete
    fun delete(continent: RoomCountry)

    @Query("SELECT * FROM RoomCountry WHERE continent = :continentName ")
    fun findForContinent(continentName: String): List<RoomCountry>
}