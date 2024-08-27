package com.david.weathermvvm.model.repository.apiclient.dto

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val code: Double,
    val message: String
)
