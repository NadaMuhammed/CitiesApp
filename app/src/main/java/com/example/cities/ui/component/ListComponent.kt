package com.example.cities.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.cities.domain.model.City

@Composable
fun ListComponent(
    cities: List<City>?,
    onClick: (City?) -> Unit
) {
    LazyColumn {
        items(cities ?: listOf()) { city ->
            CardComponent(
                city,
                onClick
            )
        }
    }
}