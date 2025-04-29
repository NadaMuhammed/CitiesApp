package com.example.cities.domain.model

data class City(
    val country: String? = null,
    val name: String? = null,
    val filteredName: String? = null,
    val id: Long? = null,
    val coordinates: Coordinates? = null
)

data class Coordinates(
    val lon: Double? = null,
    val lat: Double? = null
)