package com.example.cities.domain.repository

import com.example.cities.domain.model.City

fun interface CitiesRepository {

    suspend fun getCities(): List<City>?
}