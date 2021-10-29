package com.example.spacexlaunches.application

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * Starts Application for the code generation of the Hilt Components
 */
@HiltAndroidApp
class SpaceXLaunchesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
    companion object{
        var appContext : Context? = null
            private set
    }
}