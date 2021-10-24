package com.example.spacexlaunches.repository.company

import com.example.spacexlaunches.model.CompanyInfo
import com.example.spacexlaunches.utils.helpers.ResultHandler

interface CompanyRepository {

    suspend fun getCompanyInfo() : ResultHandler<CompanyInfo>
}