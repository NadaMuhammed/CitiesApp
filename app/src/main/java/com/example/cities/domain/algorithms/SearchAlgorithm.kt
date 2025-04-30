package com.example.cities.domain.algorithms

import com.example.cities.domain.model.City

fun interface SearchAlgorithm {

    suspend fun search(list: List<City>, value: String): List<City>
}