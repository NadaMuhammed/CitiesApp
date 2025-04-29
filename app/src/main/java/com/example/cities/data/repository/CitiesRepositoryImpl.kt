package com.example.cities.data.repository

import com.example.cities.data.local.CitiesLocalDataSource
import com.example.cities.data.mapper.CitiesMapper
import com.example.cities.domain.model.City
import com.example.cities.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesLocalDataSource: CitiesLocalDataSource,
    private val citiesMapper: CitiesMapper
) : CitiesRepository {

    override suspend fun getCities(): List<City>? {
        return citiesMapper.map(
            citiesLocalDataSource.getCities()
        )
    }
}