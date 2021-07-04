package com.example.covidmonitor.mvp.model.cache

import com.example.covidmonitor.mvp.model.entity.Continent
import com.example.covidmonitor.mvp.model.entity.room.RoomContinent
import com.example.covidmonitor.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomContinentsCache(val db: Database) : ContinentCache {
    override fun putContinents(continents: List<Continent>): Completable {
        val roomContinents = continents.map { continentItem ->
            RoomContinent(
                continentItem.name,
                continentItem.cases,
                continentItem.todayCases,
                continentItem.deaths,
                continentItem.todayDeaths,
                continentItem.recovered,
                continentItem.todayRecovered,
                continentItem.countries.toString()
            )
        }
        return db.continentDao.insert(roomContinents)
    }

    override fun getContinents() = Single.fromCallable {
        db.continentDao.getAll().map { continentItem ->
            Continent(
                continentItem.name,
                continentItem.cases,
                continentItem.todayCases,
                continentItem.deaths,
                continentItem.todayDeaths,
                continentItem.recovered,
                continentItem.todayRecovered,
                continentItem.countries.split(",").map { it.trim() }
            )
        }
    }

    override fun getContinent(name: String): Single<Continent> =
        Single.fromCallable {
            val roomContinent = db.continentDao.findByName(name)
            roomContinent?.let { continentItem ->
                Continent(
                    continentItem.name,
                    continentItem.cases,
                    continentItem.todayCases,
                    continentItem.deaths,
                    continentItem.todayDeaths,
                    continentItem.recovered,
                    continentItem.todayRecovered,
                    continentItem.countries.split(",").map { it.trim() }
                )
            }
        }
}