package com.david.weathermvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.david.weathermvvm.R
import com.david.weathermvvm.databinding.FragmentHomeBinding
import com.david.weathermvvm.viewmodel.WeatherViewModel

class HomeFragment : Fragment() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        binding = FragmentHomeBinding.bind(view.rootView)
        weatherViewModel.uiState.observe(viewLifecycleOwner) {
            binding.tvCityNameHome.text = weatherViewModel.uiState.value?.location?.name
            binding.tvTemparatureHome.text =
                weatherViewModel.uiState.value?.current?.temp_c.toString()
            binding.tvdateHome.text = weatherViewModel.uiState.value?.location?.localtime
        }
        return view
    }


}