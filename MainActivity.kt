package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ui.WeatherScreen
import com.example.weatherapp.ui.WeatherViewModel
import com.example.weatherapp.ui.WeatherViewModelFactory
import com.example.weatherapp.data.WeatherRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create repository & ViewModel factory
        val repository = WeatherRepository()
        val factory = WeatherViewModelFactory(repository)

        setContent {
            MaterialTheme {
                val vm: WeatherViewModel = viewModel(factory = factory)
                WeatherScreen(vm) // UI Composable
            }
        }
    }
}
