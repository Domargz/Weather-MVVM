package com.david.weathermvvm

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.david.weathermvvm.model.datasource.room.AppDatabase
import com.david.weathermvvm.model.datasource.room.Cities
import com.david.weathermvvm.model.datasource.room.CitiesDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WriteDatabaseTest {
    private lateinit var cityDao: CitiesDao
    private lateinit var db: AppDatabase
    private val city: Cities = Cities(1, "Mexicali", 12.0, 12.0, 20.0, 20.0, "icon")

    @Before
    fun createDB(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        cityDao = db.citiesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    fun insertData()  {
        cityDao.insert(city)
        val city = cityDao.getCity("Mexicali")
        assertEquals(city.cityName, "Mexicali")
    }
}