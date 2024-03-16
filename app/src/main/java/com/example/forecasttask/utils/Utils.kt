package com.example.forecasttask.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {

    fun Double.kelvinToCelsius(): String {
        val celsius = this - 273.15
        return String.format("%.0f", celsius)
    }


    fun String.toFormattedDate(): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat("MMM d", Locale.getDefault())

        try {
            val date = inputDateFormat.parse(this)
            if (date != null) {
                return outputDateFormat.format(date)
            }
        } catch (e: Exception) {
            Log.d("DataException", e.message.toString())
        }
        return this
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Long.convertUnixTimestampToReadableDate(): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
            .withZone(ZoneId.of("UTC"))
        return formatter.format(Instant.ofEpochSecond(this))
    }
}