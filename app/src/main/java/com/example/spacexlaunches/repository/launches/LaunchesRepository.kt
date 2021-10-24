package com.example.spacexlaunches.repository.launches

import com.example.spacexlaunches.model.CompanyInfo
import com.example.spacexlaunches.model.Launch
import com.example.spacexlaunches.utils.helpers.ResultHandler

interface LaunchesRepository {
    suspend fun getAllLaunches() : ResultHandler<List<Launch>>
}