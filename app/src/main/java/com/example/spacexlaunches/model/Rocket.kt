package com.example.spacexlaunches.model

import com.google.gson.annotations.SerializedName

data class Rocket(
    @SerializedName(value = "rocket_name")
    val name: String,
    @SerializedName(value = "rocket_type")
    val type: String
)