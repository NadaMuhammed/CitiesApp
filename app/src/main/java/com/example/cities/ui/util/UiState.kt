package com.example.cities.ui.util

sealed class UiState {

    data object Loading : UiState()

    data class Success<T>(
        val data: T
    ) : UiState()
}