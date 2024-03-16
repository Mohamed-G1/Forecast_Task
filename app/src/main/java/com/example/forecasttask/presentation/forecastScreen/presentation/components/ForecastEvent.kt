package com.example.forecasttask.presentation.forecastScreen.presentation.components

sealed class ForecastEvent {
    data class CityChanged(val lat: String, val lon: String, val isChanged: Boolean = false) :
        ForecastEvent()


}