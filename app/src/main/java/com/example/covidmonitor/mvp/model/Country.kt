package com.example.covidmonitor.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    @SerializedName("country") val name: String,
    @SerializedName("cases") val cases: String,
    @SerializedName("todayCases") val todayCases: String,
    @SerializedName("deaths") val deaths: String,
    @SerializedName("todayDeaths") val todayDeaths: String,
    @SerializedName("recovered") val recovered: String,
    @SerializedName("todayRecovered") val todayRecovered: String,
    @SerializedName("population") val population: String,
    @SerializedName("flag") val flag: String
):Parcelable
