package com.example.covidmonitor.mvp.model.cache

import com.example.covidmonitor.mvp.model.entity.Continent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ContinentCache {
    fun putContinents(continents: List<Continent>): Completable
    fun getContinents(): Single<List<Continent>>
}