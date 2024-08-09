package com.david.weathermvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.weathermvvm.model.repository.apiclient.ApiRepository
import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse

class WeatherViewModel: ViewModel() {

    private val apiRepository = ApiRepository()
    private val _uiState = MutableLiveData<CityResponse>()
    val uiState: LiveData<CityResponse> = _uiState

    suspend fun getWeather(city: String) {
        _uiState.value = apiRepository.getWeather(city)
    }

    suspend fun getWeather(lat: Double, lon: Double) {
        _uiState.value = apiRepository.getWeather(lat, lon)
    }

}