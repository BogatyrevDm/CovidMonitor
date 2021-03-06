package com.example.covidmonitor.ui

import android.content.Context
import androidx.annotation.LayoutRes
import com.example.covidmonitor.mvp.model.network.NetworkStatus
import com.example.covidmonitor.mvp.model.repo.ContinentsRepo
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpAppCompatFragment
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class AbsFragment(@LayoutRes contentLayoutId: Int) : MvpAppCompatFragment(contentLayoutId),
    HasAndroidInjector {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var scheduler: Scheduler

    @Inject
    lateinit var continentsRepo: ContinentsRepo

    @Inject
    lateinit var networkStatus: NetworkStatus

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

}