package com.example.cities.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cities.domain.model.City
import com.example.cities.domain.useCase.GetCitiesUseCase
import com.example.cities.domain.useCase.SearchForCityUseCase
import com.example.cities.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _cities: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val cities: StateFlow<UiState> = _cities.asStateFlow()

    fun getSortedCities() {
        viewModelScope.launch(Dispatchers.IO) {
            _cities.emit(
                UiState.Success(
                    getCitiesUseCase.invoke()
                )
            )
        }
    }
}