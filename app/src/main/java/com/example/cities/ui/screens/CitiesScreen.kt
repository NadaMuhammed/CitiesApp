package com.example.cities.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cities.domain.model.City
import com.example.cities.ui.component.ListComponent
import com.example.cities.ui.component.LoadingComponent
import com.example.cities.ui.component.SearchComponent
import com.example.cities.ui.util.UiState
import com.example.cities.ui.viewModel.CitiesViewModel

@Composable
fun CitiesScreen(
    viewModel: CitiesViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getSortedCities()
    }

    var searchQuery by rememberSaveable { mutableStateOf("") }
    val cities by viewModel.cities.collectAsState()

    Column {
        SearchComponent(
            searchQuery = searchQuery,
            onSearchQueryChange = {
                searchQuery = it
            },
            onSearchClick = {

            }
        )

        when (val currentState = cities) {
            is UiState.Loading -> {
                LoadingComponent()
            }

            is UiState.Success<*> -> {
                val data = currentState.data as? List<City> ?: listOf()
                ListComponent(data)
            }
        }
    }
}