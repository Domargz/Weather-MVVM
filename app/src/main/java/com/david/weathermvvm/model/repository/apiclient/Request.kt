package com.david.weathermvvm.model.repository.apiclient

import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse

interface Request {

    suspend fun getWeather(city: String): CityResponse
    suspend fun getWeather(lat: Double, lon: Double): CityResponse
}