package com.example.spacexlaunches.repository.company

import com.example.spacexlaunches.model.CompanyInfo
import com.example.spacexlaunches.network.CompanyEndpoints
import com.example.spacexlaunches.utils.helpers.ResultHandler
import retrofit2.http.Path

/**
 * CompanyRepository interface implementation
 */
class CompanyRepositoryImpl(val companyEndpoints: CompanyEndpoints) : CompanyRepository {
    override suspend fun getCompanyInfo(): ResultHandler<CompanyInfo> {
        val result = companyEndpoints.getCompanyInfo()
        return try {
            if (result.name.isNotEmpty()) {
                ResultHandler.Success(result)
            } else {
                ResultHandler.Empty()
            }
        } catch (ex: Exception) {
            ResultHandler.Error(ex)
        }
    }

}