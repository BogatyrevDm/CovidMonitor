package com.example.covidmonitor.mvp.view

import com.example.covidmonitor.mvp.model.entity.Country

interface CountryItemView : IItemView {
    fun showCountry(country: Country)
    fun loadImage(text: String)
}