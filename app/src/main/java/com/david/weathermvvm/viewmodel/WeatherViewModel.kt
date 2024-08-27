package com.david.weathermvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.weathermvvm.model.repository.apiclient.ApiRepository
import com.david.weathermvvm.model.repository.apiclient.dto.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var api: ApiRepository

    private val _uiState = MutableLiveData<Response>()
    val uiState: LiveData<Response> = _uiState


    suspend fun getWeather(city: String) {
        _uiState.value = api.getWeather(city)
    }

    suspend fun getWeather(lat: Double, lon: Double) {
        _uiState.value = api.getWeather(lat, lon)
    }

    suspend fun getWeatherFavorite(city: String): Response {
        return api.getWeather(city)
    }

}