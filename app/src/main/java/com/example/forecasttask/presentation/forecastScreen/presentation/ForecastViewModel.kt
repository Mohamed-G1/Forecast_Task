package com.example.forecasttask.presentation.forecastScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forecasttask.data.remote.NetworkState
import com.example.forecasttask.presentation.forecastScreen.domian.model.ForecastResponse
import com.example.forecasttask.presentation.forecastScreen.domian.repository.ForecastRepository
import com.example.forecasttask.presentation.forecastScreen.presentation.components.ForecastEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val repository: ForecastRepository
) : ViewModel() {

    private val _forecastState =
        MutableStateFlow<NetworkState<ForecastResponse>>(NetworkState.Empty)


    private val _state = MutableStateFlow(ForecastState())
    val state = _state.asStateFlow()

    fun onEvent(event: ForecastEvent) {
        when (event) {
            is ForecastEvent.CityChanged -> {
                callForecastApi(lat = event.lat, lon = event.lon, isChanged = event.isChanged)
            }
        }
    }

    private fun callForecastApi(
        lat: String, lon: String, isChanged: Boolean
    ) {
        viewModelScope.launch {
            repository.getForecastStream(lat = lat, lon = lon, isChanged = isChanged)
                .collectLatest { uiState ->
                    _forecastState.value = uiState
                    _state.value = mapToUiState(uiState)
                }
        }
    }

    private fun mapToUiState(uiState: NetworkState<ForecastResponse>): ForecastState {
        return when (uiState) {
            is NetworkState.Loading -> {
                _state.value.copy(isLoading = uiState.status)
            }

            is NetworkState.Success -> {
                _state.value.copy(
                    weatherData = uiState.data,
                    warningMessage = uiState.warningMessage,
                    isCachedData = uiState.isCachedData
                )
            }

            is NetworkState.Error -> {
                _state.value.copy(
                    errorMessage = uiState.error.errorDescription.toString(),
                    isCacheError = uiState.isCacheError
                )

            }

            else -> _state.value
        }

    }

}