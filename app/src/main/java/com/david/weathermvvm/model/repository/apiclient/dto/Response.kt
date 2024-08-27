package com.david.weathermvvm.model.repository.apiclient.dto


sealed class Response {
    data class Success(val data: CityResponse): Response()
    data class Failure(val message: String?): Response()
}