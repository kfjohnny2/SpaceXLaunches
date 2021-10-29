package com.example.spacexlaunches.injection.modules

import com.example.spacexlaunches.BuildConfig
import com.example.spacexlaunches.network.CompanyEndpoints
import com.example.spacexlaunches.network.LaunchesEndpoints
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Module for providing our Endpoints interface instances
 *
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModules {


    /**
     * Provides the CompanyEndpoints service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the CompanyEndpoints service implementation.
     */
    @Provides
    @Reusable
    internal fun provideCompanyEndpoints(retrofit: Retrofit): CompanyEndpoints {
        return retrofit.create(CompanyEndpoints::class.java)
    }
    /**
     * Provides the LaunchesEndpoints service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the CompanyEndpoints service implementation.
     */
    @Provides
    @Reusable
    internal fun provideLaunchesEndpoints(retrofit: Retrofit): LaunchesEndpoints {
        return retrofit.create(LaunchesEndpoints::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        httpClient.readTimeout(230, TimeUnit.SECONDS)
        httpClient.connectTimeout(230, TimeUnit.SECONDS)

        //INTERCEPTORS
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(logging)
        }

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}