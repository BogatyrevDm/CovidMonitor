package com.example.covidmonitor.di.modules

import com.example.covidmonitor.ui.fragment.ContinentsFragment
import com.example.covidmonitor.ui.fragment.CountriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {
    @ContributesAndroidInjector
    abstract fun bindContinentsFragment(): ContinentsFragment

    @ContributesAndroidInjector
    abstract fun bindCountriesFragment(): CountriesFragment
}