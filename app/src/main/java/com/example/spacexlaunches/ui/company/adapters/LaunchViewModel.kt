package com.example.spacexlaunches.ui.company.adapters

import androidx.lifecycle.MutableLiveData
import com.example.spacexlaunches.base.BaseViewModel
import com.example.spacexlaunches.model.Launch
import com.example.spacexlaunches.model.Links
import com.example.spacexlaunches.utils.extentions.formatDate

class LaunchViewModel : BaseViewModel() {
    val launchMissionPatch = MutableLiveData<String>()
    val launchMissionName = MutableLiveData<String>()
    val launchDateTime = MutableLiveData<String>()
    val launchRocket = MutableLiveData<String>()
    val launchDaysSince = MutableLiveData<String>()
    val launchWasSuccess = MutableLiveData<Boolean>()
    val launchLinks = MutableLiveData<Links>()

    fun bind(launch: Launch){
        launchMissionPatch.value = launch.links.missionPatchSmall
        launchMissionName.postValue(launch.missionName)
        launchDateTime.postValue(launch.launchDateUnix.formatDate("dd/MM/yyyy 'at' HH:mm"))
        launchRocket.postValue("${launch.rocket.name} ${launch.rocket.type}")
        launchDaysSince.postValue(launch.launchDateLocal)
        launchWasSuccess.postValue(launch.landSuccess)
        launchLinks.postValue(launch.links)
    }

    fun getMissionPatch() = launchMissionPatch.value
    fun wasSuccessful() = launchWasSuccess.value
    fun launchLinks() = launchLinks.value
}