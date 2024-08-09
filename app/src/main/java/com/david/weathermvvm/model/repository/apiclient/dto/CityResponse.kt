package com.david.weathermvvm.model.repository.apiclient.dto

import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    val current: Current,
    val location: Location
)