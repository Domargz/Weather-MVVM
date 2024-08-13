package com.david.weathermvvm.model.datasource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CitiesDao {
    @Query("SELECT * FROM cities")
    fun getAll(): List<Cities>

    @Query("SELECT * FROM cities WHERE cityName = :cityName")
    fun getCity(cityName: String): Cities

    @Insert
    fun insert(cities: Cities)

    @Query("DELETE FROM cities WHERE cityName = :cityName")
    fun deleteAll(cityName: String)

}