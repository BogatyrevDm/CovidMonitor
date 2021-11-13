package com.example.covidmonitor.navigation

import com.example.covidmonitor.ui.fragment.ContinentsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ContinentsScreen : SupportAppScreen() {
    override fun getFragment() = ContinentsFragment.newInstance()
}