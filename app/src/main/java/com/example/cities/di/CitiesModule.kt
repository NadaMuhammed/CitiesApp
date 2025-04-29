package com.example.cities.di

import com.example.cities.data.local.CitiesLocalDataSource
import com.example.cities.data.local.CitiesLocalDataSourceImpl
import com.example.cities.data.mapper.CitiesMapper
import com.example.cities.data.mapper.CitiesMapperImpl
import com.example.cities.data.repository.CitiesRepositoryImpl
import com.example.cities.domain.repository.CitiesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
fun interface CitiesDataSourceModule {

    @Singleton
    @Binds
    fun bindCitiesLocalDataSource(
        citiesLocalDataSourceImpl: CitiesLocalDataSourceImpl
    ): CitiesLocalDataSource
}

@InstallIn(ViewModelComponent::class)
@Module
interface CitiesModule {

    @Binds
    fun bindCitiesRepository(
        citiesRepositoryImpl: CitiesRepositoryImpl
    ): CitiesRepository

    @Binds
    fun bindCitiesMapper(
        citiesMapperImpl: CitiesMapperImpl
    ): CitiesMapper
}
