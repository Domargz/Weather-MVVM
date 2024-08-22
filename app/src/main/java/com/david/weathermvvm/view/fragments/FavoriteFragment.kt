package com.david.weathermvvm.view.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.david.weathermvvm.R
import com.david.weathermvvm.databinding.FragmentFavoriteBinding
import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import com.david.weathermvvm.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var cityResponse: CityResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoriteBinding.bind(view)
        val searchBar = binding.sbFavoriteCity

        searchBar.setOnKeyListener{_, keyCode, event ->

            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                val sbCityName = searchBar.text.toString()

                if(sbCityName.length > 3){
                    CoroutineScope(Dispatchers.IO).launch {
                        val deferred = async {
                            cityResponse = weatherViewModel.getWeatherFavorite(sbCityName)
                        }
                        deferred.await()

                        withContext(Dispatchers.Main){

                        }
                    }

                }

            }

            true
        }


    }

}