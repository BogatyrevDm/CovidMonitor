package com.example.covidmonitor.mvp.model.repo

import com.example.covidmonitor.mvp.model.entity.Continent
import io.reactivex.rxjava3.core.Single

interface ContinentsRepo {
    fun getContinents(): Single<List<Continent>>
    fun getContinentByName(name: String): Single<Continent>
}