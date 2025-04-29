package com.example.cities.data.local

import com.example.cities.data.dto.CityDTO

fun interface CitiesLocalDataSource {

    suspend fun getCities(): List<CityDTO>?
}