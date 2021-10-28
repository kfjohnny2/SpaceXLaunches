package com.example.spacexlaunches.model

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("mission_patch")
    val missionPatch: String,
    @SerializedName("mission_patch_small")
    val missionPatchSmall: String,
    @SerializedName("article_link")
    val articleLink: String,
    val wikipedia: String,
    @SerializedName("video_link")
    val videoLink: String
)
