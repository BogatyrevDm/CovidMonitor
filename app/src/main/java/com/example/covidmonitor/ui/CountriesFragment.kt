package com.example.covidmonitor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import com.example.covidmonitor.R.layout.fragment_countries
import android.view.View
import android.view.ViewGroup
import com.example.covidmonitor.R


class CountriesFragment : AbsFragment(fragment_countries) {
    companion object {
        @JvmStatic
        fun newInstance() =
            CountriesFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_countries, container, false)
    }


}