package com.example.covidmonitor.ui.activity

import android.os.Bundle
import com.example.covidmonitor.R
import com.example.covidmonitor.R.layout.activity_main
import com.example.covidmonitor.navigation.ContinentsScreen
import com.example.covidmonitor.ui.AbsActivity
import com.example.covidmonitor.ui.BackButtonListener
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AbsActivity(activity_main) {

    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(ContinentsScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
    }

}