package com.example.covidmonitor.di.modules

import android.widget.ImageView
import com.example.covidmonitor.mvp.model.image.IImageLoader
import com.example.covidmonitor.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun imageLoader():IImageLoader<ImageView> = GlideImageLoader()
}