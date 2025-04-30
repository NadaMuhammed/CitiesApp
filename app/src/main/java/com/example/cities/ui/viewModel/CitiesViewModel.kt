package com.example.cities.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cities.di.IoDispatcher
import com.example.cities.domain.model.City
import com.example.cities.domain.useCase.GetCitiesUseCase
import com.example.cities.domain.useCase.SearchForCityUseCase
import com.example.cities.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val searchForCityUseCase: SearchForCityUseCase
) : ViewModel() {

    private val _allCities: MutableStateFlow<List<City>?> = MutableStateFlow(null)
    val allCities: StateFlow<List<City>?> = _allCities.asStateFlow()

    private val _cities: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val cities: StateFlow<UiState> = _cities.asStateFlow()

    private val _searchQuery: MutableStateFlow<String?> = MutableStateFlow(null)
    val searchQuery = _searchQuery.asStateFlow()

    init {
        getSortedCities()
        observeSearchQuery()
    }

    private fun observeSearchQuery() {
        viewModelScope.launch(ioDispatcher) {
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    allCities.value?.let {
                        val result = if (query.isNullOrEmpty()) {
                            _allCities.value
                        } else {
                            searchForCityUseCase.invoke(
                                allCities.value ?: listOf(),
                                query
                            )
                        }
                        _cities.emit(UiState.Success(result))
                    }
                }
        }
    }

    private fun getSortedCities() {
        viewModelScope.launch(ioDispatcher) {
            _cities.emit(UiState.Loading)
            getCitiesUseCase.invoke()?.let { cities ->
                _allCities.emit(cities)
                _cities.emit(UiState.Success(cities))
            } ?: run {
                _cities.emit(UiState.EmptyState)
            }
        }
    }

    fun setSearchQuery(cityPrefix: String) {
        _searchQuery.value = cityPrefix
    }
}