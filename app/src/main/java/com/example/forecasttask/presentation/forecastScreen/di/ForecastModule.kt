package com.example.forecasttask.presentation.forecastScreen.di

import com.example.forecasttask.presentation.forecastScreen.data.network.NetworkDataSource
import com.example.forecasttask.presentation.forecastScreen.data.repository.ForecastRepositoryImpl
import com.example.forecasttask.presentation.forecastScreen.domian.repository.ForecastRepository
import com.example.forecasttask.presentation.forecastScreen.presentation.ForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val forecastModule = module {
    single {
        provideForecastService(get())
    }

    single<ForecastRepository> {
        ForecastRepositoryImpl(get(), get())
    }

    viewModel {
        ForecastViewModel(get())
    }
}

fun provideForecastService(retrofit: Retrofit): NetworkDataSource {
    return retrofit.create(NetworkDataSource::class.java)
}