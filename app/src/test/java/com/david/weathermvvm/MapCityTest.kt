package com.david.weathermvvm

import com.david.weathermvvm.domain.CityMapper
import com.david.weathermvvm.domain.usescases.GetCityUseCase
import com.david.weathermvvm.model.repository.apiclient.ApiRepository
import com.david.weathermvvm.model.repository.apiclient.dto.Response
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MapCityTest {

    @Test
    fun testMapCity() = runTest {
        val cityName = "Tijuana"
        val city = ApiRepository().getWeather(cityName)
        when (city) {
            is Response.Success -> {
                val cityToSave = GetCityUseCase(CityMapper()).invoke(city.data)
                assert(cityToSave.cityName == cityName)
            }

            is Response.Failure -> {
                assert(city.message == "Hello")
            }
        }


    }
}