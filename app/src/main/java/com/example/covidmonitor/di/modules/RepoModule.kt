package com.example.covidmonitor.di.modules

import com.example.covidmonitor.mvp.model.api.IDataSource
import com.example.covidmonitor.mvp.model.repo.ContinentsRepo
import com.example.covidmonitor.mvp.model.repo.RetrofitContinetsRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun continentsRepo(api: IDataSource): ContinentsRepo = RetrofitContinetsRepo(api)
}