package com.example.covidmonitor.navigation

import androidx.fragment.app.Fragment
import com.example.covidmonitor.ui.ContinentsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ContinentsScreen:SupportAppScreen() {
    override fun getFragment()= ContinentsFragment.newInstance()

}