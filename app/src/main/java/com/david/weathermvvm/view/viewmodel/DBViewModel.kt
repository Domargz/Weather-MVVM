package com.david.weathermvvm.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.david.weathermvvm.domain.usescases.GetCityUseCase
import com.david.weathermvvm.model.datasource.db.DataBaseRepository
import com.david.weathermvvm.model.datasource.db.room.Cities
import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DBViewModel @Inject constructor(private val repoDB: DataBaseRepository): ViewModel()  {


    @Inject
    lateinit var cityUseCase: GetCityUseCase

    var cities: LiveData<List<Cities>> = getAll()

    init {
        getAll()
    }

    private fun getAll(): LiveData<List<Cities>> =
         repoDB.getAll().asLiveData()


    suspend fun getCity(cityName: String): Cities = repoDB.getCity(cityName)

    suspend fun insert(city: CityResponse) {
        val cityToSave = cityUseCase(city)
        repoDB.insert(cityToSave)
    }

    suspend fun delete(cityName: String) = repoDB.delete(cityName)


}