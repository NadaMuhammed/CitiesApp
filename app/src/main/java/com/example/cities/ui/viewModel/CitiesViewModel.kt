package com.example.cities.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cities.di.IoDispatcher
import com.example.cities.domain.useCase.GetCitiesUseCase
import com.example.cities.domain.useCase.SearchForCityUseCase
import com.example.cities.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val searchForCityUseCase: SearchForCityUseCase
) : ViewModel() {

    private val _cities: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val cities: StateFlow<UiState> = _cities.asStateFlow()

    private val _searchQuery: MutableStateFlow<String?> = MutableStateFlow(null)
    val searchQuery = _searchQuery.asStateFlow()

    fun getSortedCities() {
        viewModelScope.launch(ioDispatcher) {
            _cities.emit(
                UiState.Loading
            )

            _cities.emit(
                UiState.Success(
                    getCitiesUseCase.invoke()
                )
            )
        }
    }

    fun searchForCity(cityPrefix: String) {
        viewModelScope.launch(ioDispatcher) {
            if (searchQuery.value != cityPrefix) {
                _cities.emit(
                    UiState.Success(
                        searchForCityUseCase.invoke(
                            Pair(
                                first = when (cities.value) {
                                    is UiState.Success -> {
                                        (cities.value as UiState.Success).data ?: listOf()
                                    }

                                    else -> {
                                        listOf()
                                    }
                                },
                                second = cityPrefix
                            )
                        )
                    )
                )
                _searchQuery.value = cityPrefix
            }
        }
    }
}