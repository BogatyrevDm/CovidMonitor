package com.example.covidmonitor.di.modules

import com.example.covidmonitor.ui.ContinentsFragment
import com.example.covidmonitor.ui.CountriesFragment
import com.example.covidmonitor.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {
    @ContributesAndroidInjector
    abstract fun bindContinentsFragment(): ContinentsFragment

    @ContributesAndroidInjector
    abstract fun bindCountriesFragment(): CountriesFragment
}