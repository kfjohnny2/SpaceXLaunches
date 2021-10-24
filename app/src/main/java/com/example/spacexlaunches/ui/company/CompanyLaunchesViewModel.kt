package com.example.spacexlaunches.ui.company

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.spacexlaunches.base.BaseViewModel
import com.example.spacexlaunches.model.CompanyInfo
import com.example.spacexlaunches.model.Launch
import com.example.spacexlaunches.network.CompanyEndpoints
import com.example.spacexlaunches.network.LaunchesEndpoints
import com.example.spacexlaunches.repository.company.CompanyRepositoryImpl
import com.example.spacexlaunches.repository.launches.LaunchesRepositoryImpl
import com.example.spacexlaunches.utils.helpers.ResultHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompanyLaunchesViewModel : BaseViewModel() {
    @Inject
    lateinit var companyEndpoints: CompanyEndpoints

    @Inject
    lateinit var launchesEndpoints: LaunchesEndpoints

    private val companyRepository by lazy { CompanyRepositoryImpl(companyEndpoints) }
    private val launchesRepository by lazy { LaunchesRepositoryImpl(launchesEndpoints) }

    val companyData = MutableLiveData<CompanyInfo>()
    val launchesList = MutableLiveData<List<Launch>>()

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        withContext(Dispatchers.IO) {
            when(val companyResponse = companyRepository.getCompanyInfo()){
                is ResultHandler.Success -> {
                    companyData.postValue(companyResponse.data)
                    when(val launchesResponse = launchesRepository.getAllLaunches()){
                        is ResultHandler.Success -> {
                            launchesList.postValue(launchesResponse.data)
                        }
                        is ResultHandler.Error ->{
                            Log.d("ERROR Launches: ", "Error while retrieving launches info")
                        }
                        is ResultHandler.Empty ->{
                            Log.d("NO DATA: ", "No data for Launches")
                        }
                    }
                }
                is ResultHandler.Error ->{
                    Log.d("ERROR COMPANY: ", "Error while retrieving company info")
                }
                is ResultHandler.Empty ->{
                    Log.d("NO DATA: ", "No data for CompanyInfo")
                }
            }
        }
    }
}