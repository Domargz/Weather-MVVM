package com.david.weathermvvm.model.repository.apiclient.dto

import kotlinx.serialization.Serializable

@Serializable
data class Current(
    val cloud: Int,
    val condition: Condition,
    val humidity: Int,
    val temp_c: Double,
    val temp_f: Double
)