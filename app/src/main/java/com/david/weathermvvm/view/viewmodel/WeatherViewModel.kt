package com.david.weathermvvm.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.weathermvvm.model.repository.apiclient.Request
import com.david.weathermvvm.model.repository.apiclient.dto.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class WeatherViewModel @Inject constructor(private val api: Request) : ViewModel() {

    private val _uiState = MutableLiveData<Response>()
    val uiState: LiveData<Response> = _uiState


    suspend fun getWeather(city: String) {
        _uiState.value = api.getWeather(city)
    }

    suspend fun getWeather(lat: Double, lon: Double) {
        _uiState.value = api.getWeather(lat, lon)
    }

    suspend fun getWeatherToSave(city: String): Response {
        return api.getWeather(city)
    }

}