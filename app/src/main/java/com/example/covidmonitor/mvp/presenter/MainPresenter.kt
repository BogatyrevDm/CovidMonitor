package com.example.covidmonitor.mvp.presenter

import com.example.covidmonitor.mvp.view.MainView
import com.example.covidmonitor.navigation.ContinentsScreen
import com.example.covidmonitor.navigation.CountriesScreen
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(ContinentsScreen())
    }

    fun backClick() = router.exit()
}