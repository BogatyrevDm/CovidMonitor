package com.example.covidmonitor.mvp.presenter

import com.example.covidmonitor.mvp.model.entity.Continent
import com.example.covidmonitor.mvp.model.entity.Country
import com.example.covidmonitor.mvp.model.repo.ContinentsRepo
import com.example.covidmonitor.mvp.model.repo.CountriesRepo
import com.example.covidmonitor.mvp.view.CountriesView
import com.example.covidmonitor.mvp.view.CountryItemView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class CountriesPresenter(
    private val continentName: String,
    private val continentsRepo: ContinentsRepo,
    private val countriesRepo: CountriesRepo,
    private val scheduler: Scheduler,
    private val router: Router
) : MvpPresenter<CountriesView>() {
    class CountriesListPresenter : CountryListPresenter {
        val countries = mutableListOf<Country>()
        override var itemClickListener: ((CountryItemView) -> Unit)? = null
        override fun getCount() = countries.size
        override fun bindView(view: CountryItemView) {
            Single.just(countries[view.pos]).subscribe({
                onBindViewSuccess(view, it)
            }, ::onBindViewError)
        }

        private fun onBindViewSuccess(view: CountryItemView, country: Country) {
            view.showCountry(country)
            country.countryInfo.flag?.let {
                view.loadImage(it)
            }

        }

        private fun onBindViewError(error: Throwable) {

        }
    }

    private val countriesListPresenter = CountriesListPresenter()
    private var disposable = CompositeDisposable()

    fun getCountriesListPresenter() = countriesListPresenter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        disposable += continentsRepo
            .getContinentByName(continentName)
            .observeOn(scheduler)
            .subscribe(
                ::onLoadCountrySuccess,
                ::onLoadDataError
            )
    }

    private fun onLoadDataError(error: Throwable) {
        router.exit()
    }

    private fun onLoadCountrySuccess(continent: Continent) {
        viewState.showContinent(continent)

        disposable += countriesRepo
            .getCountries(continent.name, continent.countries)
            .observeOn(scheduler)
            .subscribe(
                ::onLoadCountriesSuccess,
                ::onLoadDataError
            )

    }

    private fun onLoadCountriesSuccess(countries: List<Country>) {
        countriesListPresenter.countries.clear()
        countriesListPresenter.countries.addAll(countries)
        viewState.updateList()
        countriesListPresenter.itemClickListener = null
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}