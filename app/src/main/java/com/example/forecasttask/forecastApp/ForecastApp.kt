package com.example.forecasttask.forecastApp

import android.app.Application
import com.example.forecasttask.data.local.di.databaseModule
import com.example.forecasttask.data.remote.di.networkModule
import com.example.forecasttask.presentation.forecastScreen.di.forecastModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ForecastApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ForecastApp)
            modules(
                networkModule,
                forecastModule,
                databaseModule
            )
        }
    }
}