package com.example.covidmonitor.ui.activity

import android.os.Bundle
import com.example.covidmonitor.R
import com.example.covidmonitor.R.layout.activity_main
import com.example.covidmonitor.mvp.presenter.MainPresenter
import com.example.covidmonitor.mvp.view.MainView
import com.example.covidmonitor.ui.AbsActivity
import com.example.covidmonitor.ui.BackButtonListener
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : AbsActivity(activity_main), MainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)
    private val presenter by moxyPresenter {
        MainPresenter(router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
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
        presenter.backClick()
    }

}