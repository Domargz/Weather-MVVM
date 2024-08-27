package com.david.weathermvvm.domain

import com.david.weathermvvm.model.datasource.db.room.Cities
import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import javax.inject.Inject

class CityMapper @Inject constructor() : Mapper<CityResponse, Cities> {
    override fun map(from: CityResponse): Cities {
        return Cities(
            cityName = from.location!!.name,
            lat = from.location.lat,
            lon = from.location.lon ,
            tempc = from.current!!.temp_c ,
            tempf = from.current.temp_f,
            icon = from.current.condition.icon
        )
    }
}