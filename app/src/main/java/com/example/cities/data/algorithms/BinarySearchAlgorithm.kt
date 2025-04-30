package com.example.cities.data.algorithms

import com.example.cities.domain.algorithms.SearchAlgorithm
import com.example.cities.domain.model.City
import javax.inject.Inject

class BinarySearchAlgorithm @Inject constructor() : SearchAlgorithm {

    override suspend fun search(list: List<City>, value: String): List<City> {
        val startIdx = binarySearchStart(list, value)

        if (startIdx == -1) return emptyList()

        val results = mutableListOf<City>()
        var idx = startIdx

        while (idx < list.size && list[idx].filteredName?.startsWith(
                value,
                ignoreCase = true
            ) == true
        ) {
            results.add(list[idx])
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