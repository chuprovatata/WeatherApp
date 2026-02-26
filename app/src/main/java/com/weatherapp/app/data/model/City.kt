package com.weatherapp.app.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class City(
    val dockey: String,
    @SerialName("name-en")
    val nameEn: String,
    @SerialName("name-ru")
    val nameRu: String,
    @SerialName("Federal subject")
    val federalSubject: String
) {

    fun matchesSearchQuery(query: String): Boolean {
        val lowerQuery = query.lowercase()
        return nameRu.lowercase().contains(lowerQuery) ||
                nameEn.lowercase().contains(lowerQuery) ||
                federalSubject.lowercase().contains(lowerQuery)
    }
}