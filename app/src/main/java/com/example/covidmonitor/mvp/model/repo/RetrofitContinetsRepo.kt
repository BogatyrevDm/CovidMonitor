package com.example.covidmonitor.mvp.model.repo

import com.example.covidmonitor.mvp.model.api.IDataSource

class RetrofitContinetsRepo(val api:IDataSource):ContinentsRepo {
    override fun getContinents() = api.getContinents()
}