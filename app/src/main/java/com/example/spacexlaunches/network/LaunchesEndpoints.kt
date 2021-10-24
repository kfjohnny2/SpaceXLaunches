package com.example.spacexlaunches.network

import com.example.spacexlaunches.model.Launch
import retrofit2.http.GET

interface LaunchesEndpoints {
    /**
     * Get all launches info
     */
    @GET("launches")
    suspend fun getLaunches(): List<Launch>
}