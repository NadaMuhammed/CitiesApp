package com.example.cities.di

import com.example.cities.data.local.CitiesLocalDataSource
import com.example.cities.data.local.CitiesLocalDataSourceImpl
import com.example.cities.data.mapper.CitiesMapper
import com.example.cities.data.mapper.CitiesMapperImpl
import com.example.cities.data.repository.CitiesRepositoryImpl
import com.example.cities.domain.repository.CitiesRepository
import com.example.cities.domain.useCase.BinarySearchUseCase
import com.example.cities.domain.useCase.BinarySearchUseCaseImpl
import com.example.cities.domain.useCase.GetCitiesUseCase
import com.example.cities.domain.useCase.GetCitiesUseCaseImpl
import com.example.cities.domain.useCase.SearchForCityUseCase
import com.example.cities.domain.useCase.SearchForCityUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
fun interface CitiesSingletonModule {

    @Singleton
    @Binds
    abstract fun bindCitiesLocalDataSource(
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

    @Binds
    fun bindCitiesUseCase(
        getCitiesUseCaseImpl: GetCitiesUseCaseImpl
    ): GetCitiesUseCase

    @Binds
    fun bindSearchUseCase(
        searchForCityUseCaseImpl: SearchForCityUseCaseImpl
    ): SearchForCityUseCase

    @Binds
    fun bindBinarySearch(
        binarySearchUseCaseImpl: BinarySearchUseCaseImpl
    ): BinarySearchUseCase
}

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @IoDispatcher
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
