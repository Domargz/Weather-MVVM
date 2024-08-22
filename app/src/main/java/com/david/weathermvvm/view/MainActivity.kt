package com.david.weathermvvm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.david.weathermvvm.R
import com.david.weathermvvm.databinding.ActivityMainBinding
import com.david.weathermvvm.view.fragments.FavoriteFragment
import com.david.weathermvvm.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            weatherViewModel.getWeather("Mexicali")
        }
        binding.bottomNavigation.setOnClickListener{ view ->
            when(view.id){
                R.id.favorite_bottom_bar -> {
                    supportFragmentManager.commit {
                        replace(R.id.fcvContainer, FavoriteFragment())
                        setReorderingAllowed(true)
                        addToBackStack("FavoriteFragment")
                    }
                }
            }
        }
    }
}