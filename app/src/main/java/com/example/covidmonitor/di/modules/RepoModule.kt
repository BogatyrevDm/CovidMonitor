package com.example.covidmonitor.di.modules

import com.example.covidmonitor.mvp.model.api.IDataSource
import com.example.covidmonitor.mvp.model.cache.ContinentCache
import com.example.covidmonitor.mvp.model.cache.CountriesCache
import com.example.covidmonitor.mvp.model.network.NetworkStatus
import com.example.covidmonitor.mvp.model.repo.ContinentsRepo
import com.example.covidmonitor.mvp.model.repo.CountriesRepo
import com.example.covidmonitor.mvp.model.repo.RetrofitContinetsRepo
import com.example.covidmonitor.mvp.model.repo.RetrofitCountriesRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun continentsRepo(
        api: IDataSource, networkStatus: NetworkStatus, cache: ContinentCache
    ): ContinentsRepo = RetrofitContinetsRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun countriesRepo(
        api: IDataSource, networkStatus: NetworkStatus, cache: CountriesCache
    ): CountriesRepo = RetrofitCountriesRepo(api, networkStatus, cache)
}