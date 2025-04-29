package com.example.cities.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDTO(
    val country: String? = null,
    val name: String? = null,
    @SerialName("_id") val id: Long? = null,
    @SerialName("coord") val coordinates: CoordinatesDTO? = null
)

@Serializable
data class CoordinatesDTO(
    val lon: Double? = null,
    val lat: Double? = null
)
