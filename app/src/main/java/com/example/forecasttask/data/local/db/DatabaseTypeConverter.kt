package com.example.forecasttask.data.local.db

import androidx.room.TypeConverter
import com.example.forecasttask.presentation.forecastScreen.domian.model.ForecastResponse
import com.example.forecasttask.presentation.forecastScreen.domian.model.ForecastData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseTypeConverter {
    private var gson = Gson()

    @TypeConverter
    fun convertWeatherListToString(model: List<ForecastData>?): String {
        return gson.toJson(model)
    }


    @TypeConverter
    fun convertWeatherListFromString(model: String): List<ForecastData>? {
        val type = object : TypeToken<List<ForecastData>>() {}.type
        return gson.fromJson(model, type)
    }

    @TypeConverter
    fun convertModelToString(model: ForecastResponse): String {
        return gson.toJson(model)
    }


    @TypeConverter
    fun convertModelFromString(model: String): ForecastResponse {
        val type = object : TypeToken<ForecastResponse>() {}.type
        return gson.fromJson(model, type)
    }
}