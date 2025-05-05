package com.example.cities.data.local

import android.content.Context
import com.example.cities.data.dto.CityDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CitiesLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CitiesLocalDataSource {

    override suspend fun getCities(): List<CityDTO>? {
        return try {
            val inputStream = context.assets.open("cities.json")
                .bufferedReader()
                .use { it.readText() }

            val json = Json {
                ignoreUnknownKeys = true
            }

            return json.decodeFromString(
                deserializer = ListSerializer(CityDTO.serializer()),
                string = inputStream
            )
        } catch (e: Exception) {
            null
        }
    }
}