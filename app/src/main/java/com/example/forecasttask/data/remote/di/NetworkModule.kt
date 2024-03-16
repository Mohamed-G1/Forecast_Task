package com.example.forecasttask.data.remote.di

import com.example.forecasttask.data.remote.provideConverterFactory
import com.example.forecasttask.data.remote.provideOkHttpClient
import com.example.forecasttask.data.remote.provideRetrofitBuilder
import org.koin.dsl.module

val networkModule = module {

    single {
        provideConverterFactory()
    }

    single{
        provideOkHttpClient()
    }

    single{
        provideRetrofitBuilder(get(), get())
    }
}