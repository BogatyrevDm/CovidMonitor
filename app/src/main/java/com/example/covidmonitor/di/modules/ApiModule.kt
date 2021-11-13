package com.example.covidmonitor.di.modules

import android.content.Context
import androidx.room.Room
import com.example.covidmonitor.mvp.model.api.IDataSource
import com.example.covidmonitor.mvp.model.entity.room.db.Database
import com.example.covidmonitor.mvp.model.network.NetworkStatus
import com.example.covidmonitor.ui.network.AndroidNetworkStatus
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Named("baseUrl")
    @Provides
    fun provideBaseUrl() = "https://corona.lmao.ninja"

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
        .create()

    @Singleton
    @Provides
    fun provideApi(@Named("baseUrl") baseUrl: String, gson: Gson): IDataSource = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IDataSource::class.java)

    @Singleton
    @Provides
    fun provideNetworkStatus(context: Context): NetworkStatus = AndroidNetworkStatus(context)

    @Singleton
    @Provides
    fun provideDatabase(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, Database.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
}