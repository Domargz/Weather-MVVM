package com.david.weathermvvm.view.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.david.weathermvvm.R
import com.david.weathermvvm.databinding.FragmentFavoriteBinding
import com.david.weathermvvm.model.repository.apiclient.dto.Response
import com.david.weathermvvm.utils.showToast
import com.david.weathermvvm.view.adapter.FavoriteAdapter
import com.david.weathermvvm.view.viewmodel.DBViewModel
import com.david.weathermvvm.view.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private val dbViewModel by viewModels<DBViewModel>()
    private lateinit var bundle: Bundle

    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bundle = Bundle()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentFavoriteBinding.bind(view)
        val searchBar = binding.etSearchCity


        adapter = FavoriteAdapter (
            { cityName ->
                lifecycleScope.launch {
                    dbViewModel.delete(cityName)
                }
            },
            { cityName ->
                view.findNavController().navigate(R.id.action_navigation_favorite_to_details_fragment, cityName)
        })



        val recyclerView = binding.rvFavoriteCity
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        dbViewModel.cities.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        recyclerView.adapter = adapter




        searchBar.setOnKeyListener { _, keyCode, keyEvent ->

            if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
                val citySearch = binding.etSearchCity.text.toString()
                lateinit var cityToSave: Response

                if (citySearch.length > 3) {
                    lifecycleScope.launch {
                        cityToSave = weatherViewModel.getWeatherToSave(citySearch)
                        when (cityToSave) {
                            is Response.Success -> {
                                // Save to Room
                                dbViewModel.insert((cityToSave as Response.Success).data)
                            }

                            is Response.Failure -> {
                                withContext(Dispatchers.Main) {
                                    showToast(
                                        this@FavoriteFragment.requireContext(),
                                        "City not found",
                                    )
                                }
                            }
                        }
                    }
                }


            }

            true
        }
    }



}