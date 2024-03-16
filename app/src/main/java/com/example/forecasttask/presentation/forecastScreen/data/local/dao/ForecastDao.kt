package com.example.forecasttask.presentation.forecastScreen.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.forecasttask.presentation.forecastScreen.data.local.entities.ForecastEntity

import kotlinx.coroutines.flow.Flow


@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyForecast(entity: ForecastEntity)

    @Query("SELECT * FROM FORECAST_TABLE ")
    fun getDailyForecast(): Flow<List<ForecastEntity>>

}