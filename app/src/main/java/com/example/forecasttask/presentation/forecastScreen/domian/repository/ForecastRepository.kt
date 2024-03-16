package com.example.forecasttask.presentation.forecastScreen.domian.repository

import com.example.forecasttask.data.remote.NetworkState
import com.example.forecasttask.presentation.forecastScreen.domian.model.ForecastResponse
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    suspend fun getForecastStream(lat: String, lon: String, isChanged : Boolean): Flow<NetworkState<ForecastResponse>>
}