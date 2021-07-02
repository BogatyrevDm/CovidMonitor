package com.example.covidmonitor.mvp.model.repo

import com.example.covidmonitor.mvp.model.entity.Continent
import com.example.covidmonitor.mvp.model.entity.Country
import io.reactivex.rxjava3.core.Single

interface ContinentsRepo {
    fun getContinents(): Single<List<Continent>>
    fun getContinentByName(name: String): Single<Continent>
    fun getCountries(countries: List<String>): Single<List<Country>>
}