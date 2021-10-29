package com.example.spacexlaunches.ui.company.adapters

import androidx.lifecycle.MutableLiveData
import com.example.spacexlaunches.base.BaseViewModel
import com.example.spacexlaunches.model.Launch
import com.example.spacexlaunches.model.Links
import com.example.spacexlaunches.utils.extentions.DATE_AT_TIME_FORMAT
import com.example.spacexlaunches.utils.extentions.formatDate

/**
 * ViewModel for our item_launch.xml DataBinding
 */
class LaunchViewModel : BaseViewModel() {
    private val launchMissionPatch = MutableLiveData<String>()
    private val launchWasSuccess = MutableLiveData<Boolean>()
    private val launchLinks = MutableLiveData<Links>()
    val launchMissionName = MutableLiveData<String>()
    val launchDateTime = MutableLiveData<String>()
    val launchRocket = MutableLiveData<String>()
    val launchDaysSince = MutableLiveData<String>()

    fun bind(launch: Launch){
        launchMissionPatch.value = launch.links.missionPatchSmall
        launchMissionName.postValue(launch.missionName)
        launchDateTime.postValue(launch.launchDateUnix.formatDate(DATE_AT_TIME_FORMAT))
        launchRocket.postValue("${launch.rocket.name} ${launch.rocket.type}")
        launchDaysSince.postValue(launch.launchDateLocal)
        launchWasSuccess.postValue(launch.landSuccess)
        launchLinks.postValue(launch.links)
    }

    fun getMissionPatch() = launchMissionPatch.value
    fun wasSuccessful() = launchWasSuccess.value
    fun launchLinks() = launchLinks.value
}