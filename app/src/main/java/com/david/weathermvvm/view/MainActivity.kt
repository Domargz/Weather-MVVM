package com.david.weathermvvm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.david.weathermvvm.databinding.ActivityMainBinding
import com.david.weathermvvm.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewmodel:  WeatherViewModel by viewModels()

        lifecycleScope.launch {
            viewmodel.getWeather("Mexicali")
        }
    }
}