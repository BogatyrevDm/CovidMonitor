package com.example.covidmonitor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidmonitor.databinding.ItemContinentBinding
import com.example.covidmonitor.mvp.model.entity.Continent
import com.example.covidmonitor.mvp.presenter.ContinentListPresenter
import com.example.covidmonitor.mvp.view.ContinentItemView

class ContinentsRVAdapter(val presenter: ContinentListPresenter) :
    RecyclerView.Adapter<ContinentsRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemContinentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
        .apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()
    inner class ViewHolder(val vb: ItemContinentBinding) : RecyclerView.ViewHolder(vb.root),
        ContinentItemView {

        override var pos = -1

        override fun showContinent(contint: Continent) {
            vb.tvName.text = contint.name
            vb.tvCases.text = contint.cases
            vb.tvTodayCases.text = contint.todayCases
            vb.tvDeaths.text = contint.deaths
            vb.tvTodayDeaths.text = contint.todayDeaths
            vb.tvRecovered.text = contint.recovered
            vb.tvTodayRecovered.text = contint.todayRecovered
        }
    }
}