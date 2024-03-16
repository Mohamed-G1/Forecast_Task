package com.example.forecasttask.presentation.forecastScreen.data.local

import com.example.forecasttask.presentation.forecastScreen.data.local.dao.ForecastDao
import com.example.forecasttask.presentation.forecastScreen.data.local.entities.ForecastEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val dao: ForecastDao
) : LocalDataSource {

    override suspend fun insertForecastData(entity: ForecastEntity) {
        dao.insertDailyForecast(entity = entity)
    }

    override fun readForecastData(): Flow<List<ForecastEntity>> {
        return dao.getDailyForecast()
    }
}