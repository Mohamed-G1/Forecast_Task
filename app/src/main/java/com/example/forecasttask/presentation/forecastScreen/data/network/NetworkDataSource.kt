package com.example.forecasttask.presentation.forecastScreen.data.network

import com.example.forecasttask.BuildConfig
import com.example.forecasttask.presentation.forecastScreen.domian.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkDataSource {
    @GET(EndPoint.FORECAST)
   suspend fun getDailyForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): ForecastResponse
}