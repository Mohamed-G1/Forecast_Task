package com.example.forecasttask.presentation.forecastScreen.domian.model


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("city")
    val city: City? = City(),
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val forecastList: List<ForecastData>? = emptyList(),
    @SerializedName("message")
    val message: Int?
)

data class ForecastData(
    @SerializedName("dt")
    val dt: Int? = 0,
    @SerializedName("dt_txt")
    val dtTxt: String? = "",
    @SerializedName("main")
    val main: Main? = Main(),
    @SerializedName("pop")
    val pop: Double? = 0.0,
    @SerializedName("visibility")
    val visibility: Int? = 0,
    @SerializedName("weather")
    val weather: List<Weather>? = emptyList(),
    @SerializedName("wind")
    val wind: Wind? = Wind()
)

data class Wind(
    @SerializedName("speed")
    val speed: Double? = 0.0,
    @SerializedName("deg")
    val deg: Int? = 0,
    @SerializedName("gust")
    val gust: Double? = 0.0
)


data class City(
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("population")
    val population: Int? = 0,
    @SerializedName("sunrise")
    val sunrise: Long? = 0L,
    @SerializedName("sunset")
    val sunset: Long? = 0L,
    @SerializedName("timezone")
    val timezone: Int? = 0
)


data class Weather(
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("main")
    val main: String? = ""
)

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double? = 0.0,
    @SerializedName("grnd_level")
    val grndLevel: Int? = 0,
    @SerializedName("humidity")
    val humidity: Int? = 0,
    @SerializedName("pressure")
    val pressure: Int? = 0,
    @SerializedName("sea_level")
    val seaLevel: Int? = 0,
    @SerializedName("temp") // Current or initial temperature
    val temp: Double? = 0.0,
    @SerializedName("temp_kf")
    val tempKf: Double? = 0.0,
    @SerializedName("temp_max")
    val tempMax: Double? = 0.0,
    @SerializedName("temp_min")
    val tempMin: Double? = 0.0
)