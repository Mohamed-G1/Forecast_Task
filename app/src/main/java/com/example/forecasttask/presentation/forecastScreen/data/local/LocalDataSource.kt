package com.example.forecasttask.presentation.forecastScreen.data.local

import com.example.forecasttask.presentation.forecastScreen.data.local.entities.ForecastEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource  {
    suspend fun insertForecastData(entity: ForecastEntity)
    fun readForecastData(): Flow<List<ForecastEntity>>
}