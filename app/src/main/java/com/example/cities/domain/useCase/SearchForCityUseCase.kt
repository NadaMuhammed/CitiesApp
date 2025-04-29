package com.example.cities.domain.useCase

import com.example.cities.domain.model.City
import javax.inject.Inject

fun interface SearchForCityUseCase {

    suspend fun invoke(input: Pair<List<City>, String>): List<City>
}

class SearchForCityUseCaseImpl @Inject constructor(
    private val binarySearchUseCase: BinarySearchUseCase
) : SearchForCityUseCase {

    override suspend fun invoke(input: Pair<List<City>, String>): List<City> {
        return binarySearchUseCase.invoke(input)
    }
}