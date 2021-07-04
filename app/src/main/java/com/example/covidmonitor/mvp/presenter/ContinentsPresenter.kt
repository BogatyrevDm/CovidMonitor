package com.example.covidmonitor.mvp.presenter

import com.example.covidmonitor.mvp.model.entity.Continent
import com.example.covidmonitor.mvp.model.repo.ContinentsRepo
import com.example.covidmonitor.mvp.view.ContinentItemView
import com.example.covidmonitor.mvp.view.ContinentsView
import com.example.covidmonitor.navigation.CountriesScreen
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class ContinentsPresenter(
    private val continentsRepo: ContinentsRepo,
    private val scheduler: Scheduler,
    private val router: Router
) : MvpPresenter<ContinentsView>() {
    class ContinentsListPresenter : ContinentListPresenter {
        val continents = mutableListOf<Continent>()
        override var itemClickListener: ((ContinentItemView) -> Unit)? = null
        override fun getCount() = continents.size
        override fun bindView(view: ContinentItemView) {
            Single.just(continents[view.pos]).subscribe({
                onBindViewSuccess(view, it)
            }, ::onBindViewError)
        }

        private fun onBindViewSuccess(view: ContinentItemView, continent: Continent) {
            view.showContinent(continent)
        }

        private fun onBindViewError(error: Throwable) {

        }
    }

    private val continentListPresenter = ContinentsListPresenter()
    private var disposable = CompositeDisposable()

    fun getСontinentListPresenter() = continentListPresenter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        continentListPresenter.itemClickListener = { itemView ->
            router.navigateTo(
                CountriesScreen(continentListPresenter.continents[itemView.pos].name)
            )
        }

    }

    fun loadData() {
        disposable += continentsRepo.getContinents()
            .observeOn(scheduler)
            .subscribe(
                ::onLoadDataSuccess,
                ::onLoadDataError
            )
    }

    private fun onLoadDataError(error: Throwable) {
        println("Error: ${error.message}")
    }

    private fun onLoadDataSuccess(coninents: List<Continent>) {
        continentListPresenter.continents.clear()
        continentListPresenter.continents.addAll(coninents)
        viewState.updateList()
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