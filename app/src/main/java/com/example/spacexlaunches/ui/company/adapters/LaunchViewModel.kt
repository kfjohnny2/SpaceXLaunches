package com.example.spacexlaunches.ui.company.adapters

import androidx.lifecycle.MutableLiveData
import com.example.spacexlaunches.base.BaseViewModel
import com.example.spacexlaunches.model.Launch

class LaunchViewModel : BaseViewModel() {
    val launchMissionPatch = MutableLiveData<String>()
    val launchMissionName = MutableLiveData<String>()
    val launchDateTime = MutableLiveData<String>()
    val launchRocket = MutableLiveData<String>()
    val launchDaysSince = MutableLiveData<String>()
    val launchWasSuccess = MutableLiveData<Boolean>()

    fun bind(launch: Launch){
        launchMissionPatch.value = launch.links.missionPatchSmall
        launchMissionName.postValue(launch.missionName)
        launchDateTime.postValue(launch.launchDateUtc)
        launchRocket.postValue("${launch.rocket.name} ${launch.rocket.type}")
        launchDaysSince.postValue(launch.launchDateUtc)
        launchWasSuccess.postValue(launch.landSuccess)
    }

    fun getMissionPatch() = launchMissionPatch.value
    fun wasSuccessful() = launchWasSuccess.value
}