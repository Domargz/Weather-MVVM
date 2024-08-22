package com.david.weathermvvm.model.repository.apiclient

import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import javax.inject.Inject

class ApiImplementation @Inject constructor(private var api: ApiRepository) : Request {

    override suspend fun getWeather(city: String): CityResponse {
        return api.getWeather(city)
    }

    override suspend fun getWeather(lat: Double, lon: Double): CityResponse {
        return api.getWeather(lat, lon)
    }
}