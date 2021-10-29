package com.example.spacexlaunches.injection.components

import com.example.spacexlaunches.injection.modules.NetworkModules
import com.example.spacexlaunches.ui.company.CompanyLaunchesViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for ViewModels.
 */
@Singleton
@Component(modules = [NetworkModules::class])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified MainViewModel.
     * @param companyLaunchesViewModel CompanyLaunchesViewModel in which to inject the dependencies
     */
    fun inject(companyLaunchesViewModel: CompanyLaunchesViewModel)

    @Component.Factory
    interface Factory {
        // Creates our ViewModelInjector
        fun create(@BindsInstance networkModule: NetworkModules): ViewModelInjector
    }
}