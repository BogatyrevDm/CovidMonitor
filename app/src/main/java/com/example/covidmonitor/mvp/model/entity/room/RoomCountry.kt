package com.example.covidmonitor.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = RoomContinent::class,
            parentColumns = ["name"],
            childColumns = ["continent"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class RoomCountry(
    @PrimaryKey var name: String,
    var continent: String,
    var cases: String,
    var todayCases: String,
    var deaths: String,
    var todayDeaths: String,
    var recovered: String,
    var todayRecovered: String,
    var population: String,
    var flag: String
)