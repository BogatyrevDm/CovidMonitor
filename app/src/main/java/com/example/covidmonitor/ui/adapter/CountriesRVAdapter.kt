package com.example.covidmonitor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidmonitor.databinding.ItemCountryBinding
import com.example.covidmonitor.mvp.model.entity.Country
import com.example.covidmonitor.mvp.model.image.IImageLoader
import com.example.covidmonitor.mvp.presenter.CountryListPresenter
import com.example.covidmonitor.mvp.view.CountryItemView

class CountriesRVAdapter(
    val presenter: CountryListPresenter,
    val imageLoader: IImageLoader<ImageView>
) :
    RecyclerView.Adapter<CountriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }


    inner class ViewHolder(val vb: ItemCountryBinding) : RecyclerView.ViewHolder(vb.root),
        CountryItemView {
        override var pos = -1

        override fun showCountry(country: Country) {
            vb.tvName.text = country.name
            vb.tvCases.text = country.cases
            vb.tvTodayCases.text = country.todayCases
            vb.tvDeaths.text = country.deaths
            vb.tvTodayDeaths.text = country.todayDeaths
            vb.tvRecovered.text = country.recovered
            vb.tvTodayRecovered.text = country.todayRecovered

        }

        override fun loadImage(text: String) {
            imageLoader.loadInto(text, vb.ivFlag)
        }


    }

}