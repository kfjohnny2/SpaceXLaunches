package com.example.spacexlaunches.model

import com.google.gson.annotations.SerializedName

data class CompanyInfo(
    val name: String,
    val founder: String,
    val founded: Int,
    val employees: Int,
    val vehicles: Int,
    @SerializedName(value = "launch_sites")
    val launchSites: Int,
    @SerializedName(value = "test_sites")
    val testSites: Int,
    val ceo: String,
    val cto: String,
    val coo: String,
    @SerializedName(value = "cto_propulsion")
    val ctoPropulsion: String,
    val valuation: Long
)
