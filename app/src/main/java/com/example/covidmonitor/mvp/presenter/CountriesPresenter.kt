package com.example.covidmonitor.mvp.presenter

import com.example.covidmonitor.mvp.model.entity.Continent
import com.example.covidmonitor.mvp.model.entity.Country
import com.example.covidmonitor.mvp.model.repo.ContinentsRepo
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
            view.setName(country.name)
            view.setCases(country.cases)
            view.setTodayCases(country.todayCases)
            view.setDeaths(country.deaths)
            view.setTodayDeaths(country.todayDeaths)
            view.setRecovered(country.recovered)
            view.setTodayRecovered(country.todayRecovered)
            country.countryInfo.flag?.let {
                view.loadImage(it)
            }

        }

        private fun onBindViewError(error: Throwable) {

        }
    }

    val countriesListPresenter = CountriesListPresenter()
    private var disposable = CompositeDisposable()
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
        viewState.setName(continent.name)
        viewState.setCases(continent.cases)
        viewState.setTodayCases(continent.todayCases)
        viewState.setDeaths(continent.deaths)
        viewState.setTodayDeaths(continent.todayDeaths)
        viewState.setRecovered(continent.recovered)
        viewState.setTodayRecovered(continent.todayRecovered)

        disposable += continentsRepo
            .getCountries(continent.countries)
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
        countriesListPresenter.itemClickListener = { itemView ->
            //router.navigateTo(RepoScreen(userLogin, reposListPresenter.repos[itemView.pos].name))
        }
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