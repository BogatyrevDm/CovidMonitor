package com.example.covidmonitor.navigation

import com.example.covidmonitor.ui.fragment.CountriesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CountriesScreen(private val name: String) : SupportAppScreen() {
    override fun getFragment() = CountriesFragment.newInstance(name)
}