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
    val rocket: Rocket
)
