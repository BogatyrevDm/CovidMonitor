package com.example.covidmonitor.mvp.model.repo

import com.example.covidmonitor.mvp.model.api.IDataSource
import com.example.covidmonitor.mvp.model.cache.ContinentCache
import com.example.covidmonitor.mvp.model.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitContinetsRepo
    @Inject constructor(
    private val api: IDataSource,
    private val networkStatus: NetworkStatus,
    private val cache: ContinentCache
) : ContinentsRepo {
    override fun getContinents() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getContinents().flatMap { continents ->
                cache.putContinents(continents)
                    .andThen(
                        Single.just(continents)
                    )
            }

        } else {
            cache.getContinents()
        }
    }.subscribeOn(Schedulers.io())


    override fun getContinentByName(name: String) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getContinentByName(name)
            } else {
                cache.getContinent(name)
            }
        }.subscribeOn(Schedulers.io())
}