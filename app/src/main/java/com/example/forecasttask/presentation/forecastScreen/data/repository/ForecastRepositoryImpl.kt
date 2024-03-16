package com.example.forecasttask.presentation.forecastScreen.data.repository

import com.example.forecasttask.data.remote.NetworkErrorHandler
import com.example.forecasttask.data.remote.NetworkState
import com.example.forecasttask.presentation.forecastScreen.data.local.LocalDataSource
import com.example.forecasttask.presentation.forecastScreen.data.local.entities.ForecastEntity
import com.example.forecasttask.presentation.forecastScreen.data.network.NetworkDataSource
import com.example.forecasttask.presentation.forecastScreen.domian.model.ForecastResponse
import com.example.forecasttask.presentation.forecastScreen.domian.repository.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ForecastRepositoryImpl(
    private val network: NetworkDataSource,
    private val local: LocalDataSource
) : ForecastRepository {

    override suspend fun getForecastStream(
        lat: String,
        lon: String,
        isChanged: Boolean
    ): Flow<NetworkState<ForecastResponse>> {
        val localData = local.readForecastData().firstOrNull()
        return if (localData.isNullOrEmpty() || isDataExpired()) {
            callForecastApiStream(lat = lat, lon = lon)
        } else if (isChanged) {
            callForecastApiStream(lat = lat, lon = lon)
        } else {
            readForecastStream()

        }
    }

    private suspend fun callForecastApiStream(
        lat: String,
        lon: String
    ): Flow<NetworkState<ForecastResponse>> = flow {
        emit(NetworkState.Loading(true))
        val response = network.getDailyForecast(lat, lon)
        offlineCachingForecast(response = response)
        emit(NetworkState.Loading(false))
        emit(NetworkState.Success(response))
    }.flowOn(Dispatchers.IO).catch { t ->
        emit(NetworkState.Loading(false))
        emit(NetworkState.Error(NetworkErrorHandler.handleNetworkError(t)))
    }


    private fun readForecastStream() = flow {
        val localData = local.readForecastData().firstOrNull() ?: emptyList()
        emit(NetworkState.Loading(false))
        emit(
            NetworkState.Success(
                localData[0].response,
                warningMessage = "Data may be outdated",
                isCachedData = true
            )
        )

    }.flowOn(Dispatchers.IO).catch { t ->
        emit(NetworkState.Loading(false))
        emit(NetworkState.Error(NetworkErrorHandler.handleNetworkError(t), isCacheError = true))
    }


    private suspend fun offlineCachingForecast(response: ForecastResponse) {
        val entity = ForecastEntity(response = response)
        local.insertForecastData(entity = entity)
    }

    private fun isDataExpired(): Boolean {
        val lastUpdateTimestamp = System.currentTimeMillis()
        val currentTime = System.currentTimeMillis()
        return currentTime - lastUpdateTimestamp > 3600000
    }
}

