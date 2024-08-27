package com.david.weathermvvm.model.datasource.db

import com.david.weathermvvm.model.datasource.db.room.Cities

interface DataBaseRepository {

    suspend fun getAll(): List<Cities>
    suspend fun getCity(cityName: String): Cities
    suspend fun insert(cities: Cities)
    suspend fun delete(cityName: String)
}