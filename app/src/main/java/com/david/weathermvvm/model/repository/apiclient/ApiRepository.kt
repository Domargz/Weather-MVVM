package com.david.weathermvvm.model.repository.apiclient

import com.david.weathermvvm.BuildConfig
import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import com.david.weathermvvm.model.repository.apiclient.dto.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepository @Inject constructor(): Request {

    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    private val baseUrl = "http://api.weatherapi.com/v1/current.json"
    private val API_KEY = BuildConfig.API_KEY


    override suspend fun getWeather(city: String): Response = withContext(Dispatchers.IO) {
        return@withContext handleRequest(city)
    }

    override suspend fun getWeather(lat: Double, lon: Double): Response =
        withContext(Dispatchers.IO) {

            return@withContext handleRequest("$lat,$lon")
        }

    private suspend fun createResponse(result: HttpResponse): Response {
        val city = result.body<CityResponse>()
        return if (city.error == null) {
            Response.Success(city)
        } else {
            Response.Failure(city.error.message)
        }
    }

    private suspend fun handleRequest(city: String): Response {
        try {
            val result = client.get(baseUrl) {
                parameter("key", API_KEY)
                parameter("q", city)
            }
            val response = createResponse(result)
            return response
        } catch (e: Exception) {
            return Response.Failure(e.message)
        }
    }
}


