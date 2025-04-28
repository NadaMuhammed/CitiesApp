package com.example.cities.domain.model

data class City(
    val country: String? = null,
    val name: String? = null,
    val id: Long? = null,
    val coordinates: Coordinates? = null
)

data class Coordinates(
    val lon: Long? = null,
    val lat: Long? = null
)