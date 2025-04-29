package com.example.cities.domain.useCase

import com.example.cities.domain.model.City
import com.example.cities.domain.repository.CitiesRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val citiesRepository: CitiesRepository
) {

    suspend fun invoke(): List<City>? {
        return citiesRepository.getCities()?.sortedBy {
            it.filteredName
        }
    }
}