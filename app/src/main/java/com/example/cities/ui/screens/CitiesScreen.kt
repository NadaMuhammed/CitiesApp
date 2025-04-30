package com.example.cities.ui.screens

import android.content.Intent
import android.net.Uri
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
import com.example.cities.ui.component.EmptyComponent
import com.example.cities.ui.component.ListComponent
import com.example.cities.ui.component.LoadingComponent
import com.example.cities.ui.component.SearchComponent
import com.example.cities.ui.util.UiState
import com.example.cities.ui.viewModel.CitiesViewModel

@Composable
fun CitiesScreen(
    viewModel: CitiesViewModel = hiltViewModel(),
    navHostController: NavHostController
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
                viewModel.setSearchQuery(searchQuery)
            }
        )

        when (val currentState = cities) {
            is UiState.Loading -> {
                LoadingComponent()
            }

            is UiState.Success -> {
                ListComponent(
                    currentState.data ?: listOf(),
                ) { city ->
                    openCityInGoogleMaps(city, navHostController)
                }
            }

            is UiState.EmptyState -> {
                EmptyComponent()
            }
        }
    }
}

private fun openCityInGoogleMaps(city: City?, navHostController: NavHostController) {
    val lat = city?.coordinates?.lat
    val lon = city?.coordinates?.lon
    val name = city?.name ?: ""

    val uri = Uri.parse("geo:$lat,$lon?q=$lat,$lon($name)")
    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        `package` = "com.google.android.apps.maps"
    }
    navHostController.context.startActivity(intent)
}
