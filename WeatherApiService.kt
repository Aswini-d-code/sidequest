package com.example.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

// Defines the API endpoints for Retrofit
interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}
