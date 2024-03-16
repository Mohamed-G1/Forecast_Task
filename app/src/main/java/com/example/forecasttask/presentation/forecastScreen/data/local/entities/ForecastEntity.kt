package com.example.forecasttask.presentation.forecastScreen.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.forecasttask.utils.Constants
import com.example.forecasttask.presentation.forecastScreen.domian.model.ForecastResponse

@Entity(tableName = Constants.FORECAST_TABLE)
class ForecastEntity(
    var response: ForecastResponse
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}