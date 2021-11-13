package com.example.covidmonitor.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidmonitor.R.layout.fragment_countries
import com.example.covidmonitor.databinding.FragmentCountriesBinding
import com.example.covidmonitor.mvp.model.entity.Continent
import com.example.covidmonitor.mvp.model.image.IImageLoader
import com.example.covidmonitor.mvp.model.repo.CountriesRepo
import com.example.covidmonitor.mvp.presenter.CountriesPresenter
import com.example.covidmonitor.mvp.view.CountriesView
import com.example.covidmonitor.ui.AbsFragment
import com.example.covidmonitor.ui.BackButtonListener
import com.example.covidmonitor.ui.adapter.CountriesRVAdapter
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class CountriesFragment : AbsFragment(fragment_countries), CountriesView, BackButtonListener {
    companion object {
        private const val CONTINENT_NAME = "CONTINENT_NAME"

        @JvmStatic
        fun newInstance(continentName: String) =
            CountriesFragment().apply {
                arguments = Bundle().apply {
                    putString(CONTINENT_NAME, continentName)
                }
            }

    }

    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!
    private val continentName by lazy {
        arguments?.getString(CONTINENT_NAME) ?: ""
    }

    @Inject
    lateinit var countriesRepo: CountriesRepo

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private val presenter by moxyPresenter {
        CountriesPresenter(
            continentName,
            continentsRepo,
            countriesRepo,
            scheduler,
            router
        )
    }

    private var adapter: CountriesRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCountriesBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showContinent(continent: Continent) {
        binding.tvName.text = continent.name
        binding.tvCases.text = continent.cases
        binding.tvTodayCases.text = continent.todayCases
        binding.tvDeaths.text = continent.deaths
        binding.tvTodayDeaths.text = continent.todayDeaths
        binding.tvRecovered.text = continent.recovered
        binding.tvTodayRecovered.text = continent.todayRecovered
    }

    override fun init() {
        binding.rvCountries.layoutManager = LinearLayoutManager(context)
        adapter = CountriesRVAdapter(presenter.getCountriesListPresenter(), imageLoader)
        binding.rvCountries.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()


}