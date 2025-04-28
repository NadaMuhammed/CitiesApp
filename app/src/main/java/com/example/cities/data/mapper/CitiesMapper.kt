package com.example.cities.data.mapper

import com.example.cities.data.dto.CityDTO
import com.example.cities.domain.model.City

fun interface CitiesMapper {

    fun map(input: List<CityDTO>?): List<City>?
}