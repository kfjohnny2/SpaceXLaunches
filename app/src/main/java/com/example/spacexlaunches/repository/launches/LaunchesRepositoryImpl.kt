package com.example.spacexlaunches.repository.launches

import com.example.spacexlaunches.model.Launch
import com.example.spacexlaunches.network.LaunchesEndpoints
import com.example.spacexlaunches.utils.helpers.ResultHandler

/**
 * CompanyRepository interface implementation
 */
class LaunchesRepositoryImpl(val launchesEndpoints: LaunchesEndpoints) : LaunchesRepository {
    override suspend fun getAllLaunches(): ResultHandler<List<Launch>> {
        val result = launchesEndpoints.getLaunches()
        return try {
            if (result.isNotEmpty()) {
                ResultHandler.Success(result)
            } else {
                ResultHandler.Empty()
            }
        } catch (ex: Exception) {
            ResultHandler.Error(ex)
        }
    }
}