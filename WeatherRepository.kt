package com.example.weatherapp.data

import com.example.weatherapp.data.remote.RetrofitInstance
import com.example.weatherapp.data.remote.WeatherResponse

class WeatherRepository {
    suspend fun getWeather(city: String, apiKey: String): Result<WeatherResponse> {
        return runCatching { RetrofitInstance.api.getWeather(city, apiKey) }
    }
}
