package com.david.weathermvvm.fragment

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.david.weathermvvm.R
import com.david.weathermvvm.view.fragments.DetailsFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

    @Test
    fun checkTextViewsDisplayed() {
        val args = bundleOf(
            "cityName" to "London",
            "temperature" to "25°C",
            "humidity" to "80%",
            "clouds" to "Cloudy")
        // Launch the fragment
        val scenario = launchFragmentInContainer<DetailsFragment>(args)


        onView(withId(R.id.idCityNameDetails)).check(matches(withText("London")))
        onView(withId(R.id.idTemperatureDetails)).check(matches(withText("25°C")))
        onView(withId(R.id.idHumedityDetails)).check(matches(withText("80%")))
        onView(withId(R.id.idCloudsDetails)).check(matches(withText("Cloudy")))





    }
}