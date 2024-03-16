package com.example.forecasttask.data.local.di

import androidx.room.Room
import com.example.forecasttask.utils.Constants.DATABASE_NAME
import com.example.forecasttask.data.local.db.ForecastDatabase
import com.example.forecasttask.presentation.forecastScreen.data.local.LocalDataSource
import com.example.forecasttask.presentation.forecastScreen.data.local.LocalDataSourceImpl
import com.example.forecasttask.presentation.forecastScreen.data.local.dao.ForecastDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ForecastDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single<ForecastDao> {
        val db = get<ForecastDatabase>()
        db.dao()
    }

    single<LocalDataSource> {
        LocalDataSourceImpl(get())
    }
}


