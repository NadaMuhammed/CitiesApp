package com.example.cities.domain.useCase

import com.example.cities.domain.algorithms.SearchAlgorithm
import com.example.cities.domain.model.City
import javax.inject.Inject

fun interface SearchForCityUseCase {

    suspend fun invoke(cities: List<City>, prefix: String): List<City>
}

class SearchForCityUseCaseImpl @Inject constructor(
    private val binarySearchAlgorithm: SearchAlgorithm
) : SearchForCityUseCase {

    override suspend fun invoke(cities: List<City>, prefix: String): List<City> {
        return binarySearchAlgorithm.search(
            cities,
            prefix
        )
    }
}