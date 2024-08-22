package com.david.weathermvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.weathermvvm.model.repository.apiclient.ApiImplementation
import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor( private var api: ApiImplementation) : ViewModel() {


    private val _uiState = MutableLiveData<CityResponse>()
    val uiState: LiveData<CityResponse> = _uiState

    suspend fun getWeather(city: String) {
        _uiState.value = api.getWeather(city)
    }

    suspend fun getWeather(lat: Double, lon: Double) {
        _uiState.value = api.getWeather(lat, lon)
    }

    suspend fun getWeatherFavorite(city: String): CityResponse {
        return api.getWeather(city)
    }

}