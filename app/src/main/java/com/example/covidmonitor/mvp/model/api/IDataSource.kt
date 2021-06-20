package com.example.covidmonitor.mvp.model.api

import com.example.covidmonitor.mvp.model.Continent
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {
    @GET("v2/continents")
    fun getContinents(): Single<List<Continent>>
}