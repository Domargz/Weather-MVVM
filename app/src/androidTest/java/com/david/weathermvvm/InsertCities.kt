package com.david.weathermvvm

import com.david.weathermvvm.domain.usescases.GetCityUseCase
import com.david.weathermvvm.model.datasource.db.DataBaseRepository
import com.david.weathermvvm.model.datasource.db.room.AppDatabase
import com.david.weathermvvm.model.repository.apiclient.ApiRepository
import com.david.weathermvvm.model.repository.apiclient.dto.Response
import com.david.weathermvvm.viewmodel.DBViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class InsertCities {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase

    @Inject
    lateinit var repoDB: DataBaseRepository

    @Inject
    lateinit var getCityUseCase: GetCityUseCase

    @Inject
    lateinit var api: ApiRepository

    @Inject
    lateinit var dbViewModel: DBViewModel

    private lateinit var cityName: String
    private lateinit var city: Response

    @Before
    fun init() = runTest {
        hiltRule.inject()
        cityName = "Tijuana"
        city = api.getWeather(cityName)
    }


    @After
    fun tearDown() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertCity() = runTest(UnconfinedTestDispatcher()) {
        when (city) {
            is Response.Success -> {
                val cityToSave = getCityUseCase.invoke((city as Response.Success).data)
                repoDB.insert(cityToSave)
                val getCity = repoDB.getCity("Tijuana")
                assert(getCity.cityName == cityName)
            }

            is Response.Failure -> {
                assert((city as Response.Failure).message == "Hello")
            }
        }

    }


    @Test
    fun insertCityByViewModel() = runTest {
        when (city) {
            is Response.Success -> {
                dbViewModel.insert((city as Response.Success).data)
                val getCity = repoDB.getCity("Tijuana")
                assert(getCity.cityName == cityName)
            }

            is Response.Failure -> {
                assert((city as Response.Failure).message == "Hello")
            }
        }
    }


}