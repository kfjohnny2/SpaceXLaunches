package com.example.spacexlaunches.network

import com.example.spacexlaunches.model.CompanyInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface CompanyEndpoints {

    /**
     * Get the company info
     */
    @GET("info")
    suspend fun getCompanyInfo(): CompanyInfo
}