package com.example.covidmonitor.mvp.model.api

import com.example.covidmonitor.mvp.model.entity.Continent
import com.example.covidmonitor.mvp.model.entity.Country
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {
    @GET("v2/continents")
    fun getContinents(): Single<List<Continent>>

    @GET("v2/continents/{query}")
    fun getContinentByName(@Path("query") name: String): Single<Continent>

    @GET("v2/countries/{query}")
    fun getCountries(@Path("query") countries: String): Single<List<Country>>
}