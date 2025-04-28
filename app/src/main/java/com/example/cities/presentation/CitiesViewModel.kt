package com.example.cities.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cities.domain.model.City
import com.example.cities.domain.useCase.GetSortedCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getSortedCitiesUseCase: GetSortedCitiesUseCase
) : ViewModel() {

    private val _sortedCities: MutableStateFlow<List<City>?> = MutableStateFlow(null)
    val sortedCities: StateFlow<List<City>?> = _sortedCities.asStateFlow()

    fun getSortedCities() {
        viewModelScope.launch(Dispatchers.IO) {
            _sortedCities.value = getSortedCitiesUseCase.invoke()
        }
    }
}