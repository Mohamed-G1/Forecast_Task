package com.example.forecasttask.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.forecasttask.presentation.forecastScreen.data.local.dao.ForecastDao
import com.example.forecasttask.presentation.forecastScreen.data.local.entities.ForecastEntity


@Database(entities = [ForecastEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConverter::class)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun dao(): ForecastDao
}