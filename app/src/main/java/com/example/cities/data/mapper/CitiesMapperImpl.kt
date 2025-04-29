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
                filteredName = filterCityName(it.name ?: ""),
                id = it.id,
                coordinates = Coordinates(
                    lat = it.coordinates?.lat,
                    lon = it.coordinates?.lon
                )
            )
        }
    }

    private fun filterCityName(name: String): String {
        // Filters By Removing White Spaces And Special Characters
        val filteredName = name.trim()
            .split(" ")
            .joinToString("")
            .replace(
                "^[^A-Za-z]+".toRegex(),
                ""
            )

        return filteredName.lowercase().ifEmpty { "~" }
    }
}