package com.example.covidmonitor.mvp.model.repo

import com.example.covidmonitor.mvp.model.entity.Country
import io.reactivex.rxjava3.core.Single

interface CountriesRepo {
    fun getCountries(continentName: String, countries: List<String>): Single<List<Country>>
}