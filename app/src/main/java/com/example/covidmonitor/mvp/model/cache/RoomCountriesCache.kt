package com.example.covidmonitor.mvp.model.cache

import com.example.covidmonitor.mvp.model.entity.Country
import com.example.covidmonitor.mvp.model.entity.CountryInfo
import com.example.covidmonitor.mvp.model.entity.room.RoomCountry
import com.example.covidmonitor.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomCountriesCache(val db: Database) : CountriesCache {
    override fun putCountries(continent: String, countries: List<Country>): Completable {
        val roomContinent = continent.let {
            db.continentDao.findByName(it)
        } ?: throw java.lang.RuntimeException("No such continent in database")
        val roomCountries = countries.map {
            RoomCountry(
                it.name,
                roomContinent.name,
                it.cases,
                it.todayCases,
                it.deaths,
                it.todayDeaths,
                it.recovered,
                it.todayRecovered,
                it.population,
                it.countryInfo.flag.let { it } ?: ""
            )

        }
        return db.countryDao.insert(roomCountries)
    }

    override fun getCountries(continent: String): Single<List<Country>> =
        Single.fromCallable {
            val roomContinent = continent.let {
                db.continentDao.findByName(it)
            } ?: throw java.lang.RuntimeException("No such continent in database")

            db.countryDao.findForContinent(roomContinent.name).map {
                Country(
                    it.name,
                    it.cases,
                    it.todayCases,
                    it.deaths,
                    it.todayDeaths,
                    it.recovered,
                    it.todayRecovered,
                    it.population,
                    CountryInfo(it.flag)
                )
            }
        }
}