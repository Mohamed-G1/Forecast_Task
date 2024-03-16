package com.example.forecasttask.data.remote

sealed class NetworkState<out T> {
    data object Empty : NetworkState<Nothing>()
    data class Loading(val status: Boolean) : NetworkState<Nothing>()
    data class Success<out R>(
        val data: R,
        val warningMessage: String = "",
        val isCachedData: Boolean = false
    ) : NetworkState<R>()

    data class Error(val error: NetworkErrorModel, val isCacheError: Boolean = false) :
        NetworkState<Nothing>()
}