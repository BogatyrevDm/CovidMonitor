package com.example.covidmonitor.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidmonitor.R.layout.fragment_countries
import com.example.covidmonitor.databinding.FragmentContinentsBinding
import com.example.covidmonitor.mvp.presenter.ContinentsPresenter
import com.example.covidmonitor.mvp.view.ContinentsView
import com.example.covidmonitor.ui.AbsFragment
import com.example.covidmonitor.ui.BackButtonListener
import com.example.covidmonitor.ui.adapter.ContinentsRVAdapter
import moxy.ktx.moxyPresenter


class ContinentsFragment : AbsFragment(fragment_countries), ContinentsView, BackButtonListener {

    companion object {
        @JvmStatic
        fun newInstance() =
            ContinentsFragment()

    }

    private var _binding: FragmentContinentsBinding? = null
    private val binding get() = _binding!!

    val presenter by moxyPresenter {
        ContinentsPresenter(continentsRepo, scheduler, router, networkStatus)
    }
    var adapter: ContinentsRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentContinentsBinding.inflate(inflater, container, false)
            .also { _binding = it }.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvContinents.layoutManager = LinearLayoutManager(context)
        adapter = ContinentsRVAdapter(presenter.getContinentListPresenter())
        binding.rvContinents.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}