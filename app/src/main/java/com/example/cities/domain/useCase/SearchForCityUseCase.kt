package com.example.cities.domain.useCase

import com.example.cities.domain.model.City
import javax.inject.Inject

class SearchForCityUseCase @Inject constructor(
    private val binarySearchUseCase: BinarySearchUseCase
) {

    fun invoke(prefix: String, list: List<City>): List<City> {
        return binarySearchUseCase.invoke(list, prefix)
    }
}