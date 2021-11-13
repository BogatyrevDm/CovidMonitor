package com.example.covidmonitor.di.modules

import com.example.covidmonitor.mvp.model.repo.ContinentsRepo
import com.example.covidmonitor.mvp.model.repo.CountriesRepo
import com.example.covidmonitor.mvp.model.repo.RetrofitContinetsRepo
import com.example.covidmonitor.mvp.model.repo.RetrofitCountriesRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepoModule {
    @Singleton
    @Binds
    fun bindContinentsRepo(retrofitContinetsRepo: RetrofitContinetsRepo): ContinentsRepo

    @Singleton
    @Binds
    fun bindCountriesRepo(retrofitCountriesRepo: RetrofitCountriesRepo): CountriesRepo
}