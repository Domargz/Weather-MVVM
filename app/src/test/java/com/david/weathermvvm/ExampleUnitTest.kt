package com.david.weathermvvm

import com.david.weathermvvm.model.repository.apiclient.ApiRepository
import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val apiRepository = ApiRepository()
    lateinit var cityResponse: CityResponse


    @Test
    fun getWeather() = runTest {
        cityResponse = apiRepository.getWeather("London")
        assertEquals(cityResponse.location.name, "London")
    }

    @Test
    fun getWeatherByLatLong() = runTest {
        cityResponse = apiRepository.getWeather(51.52, -0.11)
        assertEquals(cityResponse.location.name, "London")
    }
}