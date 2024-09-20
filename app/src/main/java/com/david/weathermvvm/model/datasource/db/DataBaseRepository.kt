package com.david.weathermvvm.model.datasource.db

import com.david.weathermvvm.model.datasource.db.room.Cities
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {

    fun getAll(): Flow<List<Cities>>
    suspend fun getCity(cityName: String): Cities
    suspend fun insert(cities: Cities)
    suspend fun delete(cityName: String)
}