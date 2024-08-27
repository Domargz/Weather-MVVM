package com.david.weathermvvm

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnitRunner
import com.david.weathermvvm.model.datasource.db.DataBaseRepository
import com.david.weathermvvm.model.datasource.db.room.AppDatabase
import com.david.weathermvvm.model.datasource.db.room.Cities
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase.assertTrue
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
@SmallTest
class CitiesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    @Inject
    lateinit var repoDB: DataBaseRepository

    @Before
    fun setup() {
        hiltRule.inject()

    }

    @After
    fun tearDown() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertUser() = runTest(UnconfinedTestDispatcher()) {
        val city = Cities(1, "Mexicali", 12.0, 12.0, 20.0, 20.0, "icon")
        repoDB.insert(city)
        val getCity = repoDB.getCity("Mexicali")
        assert(getCity == city)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteUser() = runTest(UnconfinedTestDispatcher()) {
        repoDB.delete("Mexicali")
        val getCity = repoDB.getAll()
        assertTrue(getCity.isEmpty())
    }
}

class HiltTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
