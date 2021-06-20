package com.example.covidmonitor.mvp.model.repo

import com.example.covidmonitor.mvp.model.Country
import io.reactivex.rxjava3.core.Single

interface CountriesRepo {
    fun getCountries():Single<List<Country>>
}