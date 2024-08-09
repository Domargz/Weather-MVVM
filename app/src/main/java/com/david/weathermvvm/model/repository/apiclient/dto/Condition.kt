package com.david.weathermvvm.model.repository.apiclient.dto

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val icon: String
)