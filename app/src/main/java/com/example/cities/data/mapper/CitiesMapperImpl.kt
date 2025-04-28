package com.example.cities.data.mapper

import com.example.cities.data.dto.CityDTO
import com.example.cities.domain.model.City
import com.example.cities.domain.model.Coordinates
import javax.inject.Inject

class CitiesMapperImpl @Inject constructor() : CitiesMapper {

    override fun map(input: List<CityDTO>?): List<City>? {
        return input?.map {
            City(
                country = it.country,
                name = it.name,
                id = it.id,
                coordinates = Coordinates(
                    lat = it.coordinates?.lat,
                    lon = it.coordinates?.lon
                )
            )
        }
    }
}