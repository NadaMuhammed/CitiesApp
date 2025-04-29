package com.example.cities.domain.useCase

import com.example.cities.domain.model.City
import javax.inject.Inject

fun interface BinarySearchUseCase {

    suspend fun invoke(input: Pair<List<City>, String>): List<City>
}

class BinarySearchUseCaseImpl @Inject constructor() : BinarySearchUseCase {

    override suspend fun invoke(input: Pair<List<City>, String>): List<City> {
        val startIdx = binarySearchStart(input.first, input.second)

        if (startIdx == -1) return emptyList()

        val results = mutableListOf<City>()
        var idx = startIdx

        while (idx < input.first.size && input.first[idx].filteredName?.startsWith(
                input.second,
                ignoreCase = true
            ) == true
        ) {
            results.add(input.first[idx])
            idx++
        }

        return results
    }

    private fun binarySearchStart(cities: List<City>, prefix: String): Int {
        var low = 0
        var high = cities.size - 1

        while (low <= high) {
            val mid = low + (high - low) / 2
            val cityName = cities[mid].filteredName ?: ""

            if (cityName.startsWith(prefix, ignoreCase = true)) {
                if (mid == 0 || cities[mid - 1].filteredName?.startsWith(
                        prefix,
                        ignoreCase = true
                    ) == false
                ) {
                    return mid
                }
                high = mid - 1
            } else if (cityName < prefix) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return -1
    }
}