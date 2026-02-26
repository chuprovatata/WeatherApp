package com.weatherapp.app.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.IOException

class CityRepository(private val context: Context) {

    private val json = Json {
        ignoreUnknownKeys = true
        classDiscriminator = "dockey"
    }

    fun loadCities(): Flow<List<City>> = flow {
        try {
            val cities = loadCitiesFromAssets()
            emit(cities)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(emptyList())
        }
    }

    private suspend fun loadCitiesFromAssets(): List<City> = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open("russian-cities.json")
                .bufferedReader()
                .use { it.readText() }

            json.decodeFromString<List<City>>(jsonString)
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun searchCities(query: String, cities: List<City>): List<City> {
        if (query.length < 2) return emptyList()

        val lowerQuery = query.lowercase()
        return cities.filter { city ->
            city.matchesSearchQuery(lowerQuery)
        }.take(30)
    }
}