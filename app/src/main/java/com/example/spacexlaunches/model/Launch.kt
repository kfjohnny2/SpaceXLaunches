package com.example.spacexlaunches.model

import com.google.gson.annotations.SerializedName

data class Launch(
    @SerializedName(value = "flight_number")
    val flightNumber: Int,
    @SerializedName(value = "mission_name")
    val missionName: String,
    @SerializedName(value = "launch_date_utc")
    val launchDateUtc: String,
    @SerializedName(value = "launch_date_unix")
    val launchDateUnix: Long,
    @SerializedName(value = "launch_date_local")
    val launchDateLocal: String,
    @SerializedName(value = "land_success")
    val landSuccess: Boolean,
    val rocket: Rocket,
    val links: Links
)
