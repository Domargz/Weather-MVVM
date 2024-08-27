package com.david.weathermvvm.model.datasource.db.room

import com.david.weathermvvm.model.datasource.db.DataBaseRepository
import javax.inject.Inject

class DataBaseRepositoryImp @Inject constructor(private val citiesDao: CitiesDao): DataBaseRepository {
    override suspend fun getAll(): List<Cities> {
        return citiesDao.getAll()
    }

    override suspend fun getCity(cityName: String): Cities {
        return citiesDao.getCity(cityName)
    }

    override suspend fun insert(cities: Cities) {
        citiesDao.insert(cities)
    }

    override suspend fun delete(cityName: String) {
        citiesDao.delete(cityName)
    }
}