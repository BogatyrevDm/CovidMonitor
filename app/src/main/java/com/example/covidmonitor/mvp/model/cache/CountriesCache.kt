package com.example.covidmonitor.mvp.model.cache

import com.example.covidmonitor.mvp.model.entity.Country
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface CountriesCache {
    fun putCountries(continent: String, countries: List<Country>): Completable
    fun getCountries(continent: String): Single<List<Country>>
}