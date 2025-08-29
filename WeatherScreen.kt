package com.example.weatherapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.remote.WeatherResponse
import com.example.weatherapp.BuildConfig

@Composable
fun WeatherScreen(vm: WeatherViewModel) {
    var city by remember { mutableStateOf("") }
    val weather by vm.weather
    val error by vm.error
    val loading by vm.loading

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter city name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { vm.fetchWeather(city, BuildConfig.API_KEY) },
            enabled = city.isNotBlank() && !loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (loading) "Loading..." else "Get Weather")
        }

        Spacer(Modifier.height(24.dp))

        when {
            loading -> CircularProgressIndicator()
            error != null -> Text("Error: $error", color = MaterialTheme.colorScheme.error)
            weather != null -> WeatherResult(weather!!)
        }
    }
}

@Composable
fun WeatherResult(weather: WeatherResponse) {
    Column {
        Text("City: ${weather.name}", style = MaterialTheme.typography.titleLarge)
        Text("Temp: ${weather.main.temp} °C")
        Text("Humidity: ${weather.main.humidity} %")
        Text("Condition: ${weather.weather[0].description}")
    }
}
