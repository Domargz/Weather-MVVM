package com.david.weathermvvm.model.datasource.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CitiesDao {
    @Query("SELECT * FROM cities")
    fun getAll(): Flow<List<Cities>>

    @Query("SELECT * FROM cities WHERE cityName = :cityName")
    suspend fun getCity(cityName: String): Cities

    @Insert
    suspend fun insert(cities: Cities)

    @Query("DELETE FROM cities WHERE cityName = :cityName")
    suspend fun delete(cityName: String)

}