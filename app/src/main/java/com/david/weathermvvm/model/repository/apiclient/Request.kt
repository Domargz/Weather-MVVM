package com.david.weathermvvm.model.repository.apiclient

import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import com.david.weathermvvm.model.repository.apiclient.dto.Response

interface Request {

    suspend fun getWeather(city: String): Response
    suspend fun getWeather(lat: Double, lon: Double): Response
}