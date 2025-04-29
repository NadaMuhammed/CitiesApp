package com.example.cities.ui.util

import com.example.cities.domain.model.City

sealed class UiState {

    data object Loading : UiState()

    data class Success(
        val data: List<City>?
    ) : UiState()
}