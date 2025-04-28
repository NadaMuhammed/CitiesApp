package com.example.cities.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CityDTO(
    val country: String? = null,
    val name: String? = null,
    @SerializedName("_id") val id: Long? = null,
    @SerializedName("coord") val coordinates: CoordinatesDTO? = null
)

@Serializable
data class CoordinatesDTO(
    val lon: Long? = null,
    val lat: Long? = null
)
