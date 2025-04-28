package com.example.cities.domain.useCase

import com.example.cities.domain.model.City

fun interface GetSortedCitiesUseCase {

    suspend fun invoke(): List<City>?
}