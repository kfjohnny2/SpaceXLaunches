package com.example.spacexlaunches.base

import androidx.lifecycle.ViewModel
import com.example.spacexlaunches.injection.components.DaggerViewModelInjector
import com.example.spacexlaunches.injection.components.ViewModelInjector
import com.example.spacexlaunches.injection.modules.NetworkModules
import com.example.spacexlaunches.ui.company.CompanyLaunchesViewModel

open class BaseViewModel : ViewModel() {
    private val injectorApi: ViewModelInjector = DaggerViewModelInjector.factory().create(NetworkModules)

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CompanyLaunchesViewModel -> injectorApi.inject(this)
        }
    }
}