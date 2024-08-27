package com.david.weathermvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.david.weathermvvm.R
import com.david.weathermvvm.databinding.FragmentHomeBinding
import com.david.weathermvvm.model.repository.apiclient.dto.Response
import com.david.weathermvvm.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        lifecycleScope.launch {
            weatherViewModel.getWeather("Mexicali")
        }

        binding = FragmentHomeBinding.bind(view.rootView)
        weatherViewModel.uiState.observe(viewLifecycleOwner) { response ->
            when(response ){
                is Response.Success -> {
                    val city = response.data
                    binding.tvCityNameHome.text = city.location?.name
                    binding.tvTemparatureHome.text = city.current?.temp_c.toString()
                    binding.tvdateHome.text = city.location?.localtime
                }

                is Response.Failure -> TODO()
            }

        }
        return view
    }
}