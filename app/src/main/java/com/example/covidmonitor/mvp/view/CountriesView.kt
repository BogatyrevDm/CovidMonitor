package com.example.covidmonitor.mvp.view

import com.example.covidmonitor.mvp.model.entity.Continent
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CountriesView : MvpView {
    fun showContinent(continent: Continent)
    fun init()
    fun updateList()
}