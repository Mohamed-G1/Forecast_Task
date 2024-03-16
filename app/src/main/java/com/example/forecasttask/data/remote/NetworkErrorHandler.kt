package com.example.forecasttask.data.remote

import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class NetworkErrorHandler {
    companion object {
        fun handleNetworkError(t: Throwable): NetworkErrorModel {
            try{
                if (t is HttpException && t.code() == 500) {
                    return NetworkErrorModel(errorCode = "500", errorDescription = t.message().toString())
                } else if (t is HttpException && t.code() == 400) {
                    val errorBody = t.response()?.errorBody()?.string()
                    if (!errorBody.isNullOrEmpty()) {
                        return Gson().fromJson(errorBody, NetworkErrorModel::class.java)
                    }
                } else if (t is HttpException && t.code() == 403) {
                    return NetworkErrorModel("403", t.message().toString())
                } else if (t is HttpException && t.code() == 401) {
                    return NetworkErrorModel("401", t.message().toString())
                } else if (t is IOException){
                    return NetworkErrorModel(errorCode = "No Internet", errorDescription = "Check your internet connection and try again.")
                }
                return NetworkErrorModel("null", "null")
            } catch (e: Exception){
                return NetworkErrorModel(errorCode = "", errorDescription = e.message.toString())
            }
        }
    }
}