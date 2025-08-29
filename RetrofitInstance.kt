package com.example.weatherapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    val api: WeatherApiService = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()) // 👈 Use Gson
        .build()
        .create(WeatherApiService::class.java)
}
