package com.example.cities.domain.useCase

import com.example.cities.domain.model.City
import com.example.cities.domain.repository.CitiesRepository
import javax.inject.Inject

fun interface GetCitiesUseCase {

    suspend fun invoke(): List<City>?
}

class GetCitiesUseCaseImpl @Inject constructor(
    private val citiesRepository: CitiesRepository
) : GetCitiesUseCase {

    override suspend fun invoke(): List<City>? {
        return citiesRepository.getCities()?.sortedBy {
            it.filteredName
        }
    }
}