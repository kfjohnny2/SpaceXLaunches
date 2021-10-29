package com.example.spacexlaunches.base

import androidx.lifecycle.ViewModel
import com.example.spacexlaunches.injection.components.DaggerViewModelInjector
import com.example.spacexlaunches.injection.components.ViewModelInjector
import com.example.spacexlaunches.injection.modules.NetworkModules
import com.example.spacexlaunches.ui.company.CompanyLaunchesViewModel


/**
 * BaseViewModel for defining default behaviour for the application ViewModels
 * Setups the DI components and allows it to inject any interface provided by the {@link NetworkModules}
 *
 */
open class BaseViewModel : ViewModel() {
    // Injector object with our Module factory instance
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