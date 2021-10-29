package com.example.spacexlaunches.ui.company

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.spacexlaunches.base.BaseViewModel
import com.example.spacexlaunches.model.CompanyInfo
import com.example.spacexlaunches.model.Filters
import com.example.spacexlaunches.model.Launch
import com.example.spacexlaunches.network.CompanyEndpoints
import com.example.spacexlaunches.network.LaunchesEndpoints
import com.example.spacexlaunches.repository.company.CompanyRepositoryImpl
import com.example.spacexlaunches.repository.launches.LaunchesRepositoryImpl
import com.example.spacexlaunches.utils.enum.SortType
import com.example.spacexlaunches.utils.extentions.DATE_AT_TIME_FORMAT
import com.example.spacexlaunches.utils.extentions.formatDate
import com.example.spacexlaunches.utils.helpers.ResultHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
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
    val errorMessage = MutableLiveData<String>()
    val filteredLaunchesList = MutableLiveData<List<Launch>>()

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        withContext(Dispatchers.IO) {
            when (val companyResponse = companyRepository.getCompanyInfo()) {
                is ResultHandler.Success -> {
                    companyData.postValue(companyResponse.data)
                    when (val launchesResponse = launchesRepository.getAllLaunches()) {
                        is ResultHandler.Success -> {
                            launchesList.postValue(launchesResponse.data)
                        }
                        is ResultHandler.Error -> {
                            errorMessage.value = "Error while retrieving launches info"
                        }
                        is ResultHandler.Empty -> {
                            Log.d("NO DATA: ", "No data for Launches")
                        }
                    }
                }
                is ResultHandler.Error -> {
                    errorMessage.value = "Error while retrieving company info"
                }
                is ResultHandler.Empty -> {
                    Log.d("NO DATA: ", "No data for CompanyInfo")
                }
            }
        }
    }

    fun filterLaunches(filters: Filters) {
        if (filters.year == null && filters.wasSuccess == null && filters.sortType == null){
            filteredLaunchesList.value = launchesList.value
            return
        }
        if(filters.year != null){
            filteredLaunchesList.value = launchesList.value?.filter { launch ->
                LocalDateTime.ofInstant(Instant.ofEpochSecond(launch.launchDateUnix),TimeZone.getDefault().toZoneId()).year == filters.year
            }
        }
        if (filters.wasSuccess != null){
            filteredLaunchesList.value = launchesList.value?.filter { launch ->
                launch.landSuccess == filters.wasSuccess
            }
        }
        if(filters.sortType != null){
            filteredLaunchesList.value = when(filters.sortType){
                SortType.ASC -> filteredLaunchesList.value?.sortedBy { launch-> launch.launchDateUnix.formatDate(DATE_AT_TIME_FORMAT) }
                SortType.DESC -> filteredLaunchesList.value?.sortedByDescending { launch-> launch.launchDateUtc.format(DATE_AT_TIME_FORMAT) }
                else -> filteredLaunchesList.value?.sortedBy { launch-> launch.launchDateUnix.formatDate(DATE_AT_TIME_FORMAT) }
            }

        }
        Log.i("FILTERED LIST: ", launchesList.value.toString())
    }
}