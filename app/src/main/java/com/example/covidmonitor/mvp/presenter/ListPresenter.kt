package com.example.covidmonitor.mvp.presenter

import com.example.covidmonitor.mvp.view.IItemView

interface ListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}