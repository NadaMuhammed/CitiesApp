package com.example.cities.domain.useCase

import com.example.cities.domain.model.City
import com.example.cities.domain.repository.CitiesRepository
import javax.inject.Inject

class GetSortedUseCaseImpl @Inject constructor(
    private val citiesRepository: CitiesRepository
) : GetSortedCitiesUseCase {

    override suspend fun invoke(): List<City>? {
        return citiesRepository.getCities()?.sortedBy {
            it.name
        }
    }
}