package com.david.weathermvvm.model.datasource.db.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cities(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cityName: String,
    val lat: Double,
    val lon: Double,
    val tempc: Double,
    val tempf: Double,
    val icon: String
)
