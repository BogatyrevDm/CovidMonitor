package com.example.covidmonitor.di

import android.content.Context
import com.example.covidmonitor.di.modules.ApiModule
import com.example.covidmonitor.di.modules.MainModule
import com.example.covidmonitor.di.modules.RepoModule
import com.example.covidmonitor.di.modules.UiModule
import com.example.covidmonitor.ui.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.reactivex.rxjava3.core.Scheduler
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApiModule::class,
        RepoModule::class,
        UiModule::class,
        MainModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withScheduler(scheduler: Scheduler): Builder

        fun build(): AppComponent
    }
}