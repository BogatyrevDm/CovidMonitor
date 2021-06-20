package com.example.covidmonitor.navigation

import androidx.fragment.app.Fragment
import com.example.covidmonitor.ui.CountriesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CountriesScreen : SupportAppScreen() {
    override fun getFragment() = CountriesFragment.newInstance()
}