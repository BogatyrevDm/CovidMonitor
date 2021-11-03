package com.example.covidmonitor.mvp.model.repo

import com.example.covidmonitor.mvp.model.api.IDataSource
import com.example.covidmonitor.mvp.model.cache.CountriesCache
import com.example.covidmonitor.mvp.model.entity.Country
import com.example.covidmonitor.mvp.model.entity.CountryInfo
import com.example.covidmonitor.mvp.model.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitCountriesRepo
@Inject constructor(
    private val api: IDataSource,
    private val networkStatus: NetworkStatus,
    private val cache: CountriesCache
) : CountriesRepo {

    override fun getCountries(
        countinentName: String,
        countries: List<String>

    ): Single<List<Country>> =

        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
//                api.getCountries(countries.joinToString(",")).flatMap {
//                    cache.putCountries(countinentName, it).andThen(Single.just(it))
                Single.just(
                    listOf(
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo("")),
                        Country("Albania", "0", "0", "0", "0", "0", "0", "0", CountryInfo(""))

                    )
                )
//                api.getCountries(countries.joinToString(",")).flatMap {
//                    cache.putCountries(countinentName, it).andThen(Single.just(it))
//
//            }


            } else {
                cache.getCountries(countinentName)
            }

        }.subscribeOn(Schedulers.io())
}