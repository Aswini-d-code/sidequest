package com.example.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.remote.WeatherResponse
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {
    private val _weather = mutableStateOf<WeatherResponse?>(null)
    val weather: State<WeatherResponse?> = _weather

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            val result = repo.getWeather(city, apiKey)
            result.onSuccess { _weather.value = it }
                .onFailure { _error.value = it.message }
            _loading.value = false
        }
    }
}
