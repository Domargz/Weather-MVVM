package com.david.weathermvvm.domain.usescases

import com.david.weathermvvm.domain.CityMapper
import com.david.weathermvvm.model.datasource.db.room.Cities
import com.david.weathermvvm.model.repository.apiclient.dto.CityResponse
import javax.inject.Inject

class GetCityUseCase @Inject constructor(private val cityMaper: CityMapper)  {
    operator fun invoke(cityFrom: CityResponse): Cities {
        return cityMaper.map(cityFrom)
    }

}