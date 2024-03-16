package com.example.forecasttask.presentation.forecastScreen.presentation

import com.example.forecasttask.presentation.forecastScreen.domian.model.ForecastResponse

data class ForecastState(
    val weatherData: ForecastResponse? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isCachedData: Boolean = false,
    val isCacheError: Boolean = false,
    val warningMessage: String? = ""
)
