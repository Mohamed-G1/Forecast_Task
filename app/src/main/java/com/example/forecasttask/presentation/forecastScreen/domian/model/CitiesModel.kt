package com.example.forecasttask.presentation.forecastScreen.domian.model

data class CitiesModel(
    val id: Int,
    val cityNameAr: String,
    val cityNameEn: String,
    val lat: Double,
    val lon: Double
) {

    companion object {
        val citiesList = mutableListOf(
            CitiesModel(
                id = 1,
                cityNameAr = "القاهرة",
                cityNameEn = "Cairo",
                lat = 30.0444,
                lon = 31.2357
            ),

            CitiesModel(
                id = 2,
                cityNameAr = "دبي",
                cityNameEn = "Dubai",
                lat = 25.2048,
                lon = 55.2708
            ),
            CitiesModel(
                id = 3,
                cityNameAr = "باريس",
                cityNameEn = "Paris",
                lat = 48.8566,
                lon = 2.3522
            ),

            CitiesModel(
                id = 4,
                cityNameAr = "لندن",
                cityNameEn = "London",
                lat = 51.5074,
                lon = -0.1278
            ),

            CitiesModel(
                id = 5,
                cityNameAr = "نيويورك",
                cityNameEn = "New York",
                lat = 40.7128,
                lon = -74.0060
            ),
            CitiesModel(
                id = 6,
                cityNameAr = "طوكيو",
                cityNameEn = "Tokyo",
                lat = 35.6762,
                lon = 139.6503
            ),
            CitiesModel(
                id = 7,
                cityNameAr = "سنغافورة",
                cityNameEn = "Singapore",
                lat = 1.3521,
                lon = 103.8198
            ),
            CitiesModel(
                id = 8,
                cityNameAr = "ريو دي جانيرو",
                cityNameEn = "Rio de Janeiro",
                lat = -22.9068,
                lon = -43.1729
            ),
            CitiesModel(
                id = 9,
                cityNameAr = "سيدني",
                cityNameEn = "Sydney",
                lat = -33.8688,
                lon = 151.2093
            ),
            CitiesModel(
                id = 10,
                cityNameAr = "موسكو",
                cityNameEn = "Moscow",
                lat = 55.7558,
                lon = 37.6173
            ),
        )
    }
}

