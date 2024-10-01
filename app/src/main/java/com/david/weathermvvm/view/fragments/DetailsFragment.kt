package com.david.weathermvvm.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.david.weathermvvm.R
import com.david.weathermvvm.databinding.FragmentDetailsBinding
import com.david.weathermvvm.model.BundleKeysCity
import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import com.david.weathermvvm.model.repository.apiclient.dto.Response
import com.david.weathermvvm.view.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel: WeatherViewModel by viewModels()
    private var args : Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        args = arguments
        val cityName = args?.getString(BundleKeysCity().CITY_NAME)
        lifecycleScope.launch {
            if (cityName != null) {
                Log.d("cityName", cityName)
                weatherViewModel.getWeather(cityName)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return _binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val response = weatherViewModel.uiState.observe(viewLifecycleOwner){ response ->
            handleResponse(response)
        }
    }

    private fun fullScreen(city: CityResponse) {
        // Update UI with the received data
        binding.idCityNameDetails.text = city.location?.name
        binding.idTemperatureDetails.text = city.current?.temp_c.toString()
        binding.idHumedityDetails.text = city.current?.humidity.toString()
        binding.idCloudsDetails.text = city.current?.cloud.toString()
        //binding.idIcon.text = city.current?.condition?.icon
    }

    private fun handleResponse(response: Response) {
        when (response) {
            is Response.Success -> {
                val city = response.data
                fullScreen(city)
            }
            is Response.Failure -> {
                val message = getString(R.string.city_error, response.message)
                binding.idCityNameDetails.text = message
            }
        }
    }



}

