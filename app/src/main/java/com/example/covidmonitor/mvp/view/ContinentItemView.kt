package com.example.covidmonitor.mvp.view

import com.example.covidmonitor.mvp.model.entity.Continent

interface ContinentItemView : IItemView {
    fun showContinent(contint:Continent)
}