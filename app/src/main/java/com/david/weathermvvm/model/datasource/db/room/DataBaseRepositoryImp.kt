package com.david.weathermvvm.model.datasource.db.room

import com.david.weathermvvm.model.datasource.db.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataBaseRepositoryImp @Inject constructor(private val citiesDao: CitiesDao): DataBaseRepository {
    override fun getAll(): Flow<List<Cities>> = citiesDao.getAll()


    override suspend fun getCity(cityName: String): Cities = withContext(Dispatchers.IO) {
        citiesDao.getCity(cityName)
    }

    override suspend fun insert(cities: Cities) = withContext(Dispatchers.IO) {
        citiesDao.insert(cities)
    }

    override suspend fun delete(cityName: String)  = withContext(Dispatchers.IO) {
        citiesDao.delete(cityName)
    }
}