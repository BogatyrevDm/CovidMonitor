package com.example.covidmonitor.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}