package com.david.weathermvvm.model.repository.apiclient

import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json

class ApiRepository {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    private val baseUrl = "http://api.weatherapi.com/v1/current.json"
    private val API_KEY = "2421d2e913ca463baf5232059241907"

    suspend fun getWeather(city: String): CityResponse {
        return client.get(baseUrl){
            parameter("key", API_KEY)
            parameter("q", city)
        }.body<CityResponse>()
    }

    suspend fun getWeather(lat: Double, lon: Double): CityResponse {
        return client.get(baseUrl){
            parameter("key", API_KEY)
            parameter("q", "$lat,$lon")
        }.body<CityResponse>()
    }
}