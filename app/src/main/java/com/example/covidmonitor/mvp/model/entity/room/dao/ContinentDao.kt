package com.example.covidmonitor.mvp.model.entity.room.dao

import androidx.room.*
import com.example.covidmonitor.mvp.model.entity.room.RoomContinent
import io.reactivex.rxjava3.core.Completable

@Dao
interface ContinentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(continents: List<RoomContinent>):Completable

    @Update
    fun update(continent: RoomContinent)

    @Delete
    fun delete(continent: RoomContinent)

    @Query("SELECT * FROM RoomContinent")
    fun getAll(): List<RoomContinent>

    @Query("SELECT * FROM RoomContinent WHERE name= :name LIMIT 1")
    fun findByName(name:String): RoomContinent?
}