package com.weatherapp.app.utils

import android.util.Log
import com.weatherapp.app.data.City
import com.weatherapp.app.data.ForecastPeriod

object UrlBuilder {
    private const val BASE_URL = "https://yandex.ru/pogoda/ru"
    private const val TAG = "UrlBuilder"

    fun buildYandexUrl(city: City, period: ForecastPeriod): String {
        val cityPath = city.dockey

        val url = "$BASE_URL/$cityPath/${period.yandexPath}"

        Log.d(TAG, "Город: ${city.nameRu}")
        Log.d(TAG, "dockey: $cityPath")
        Log.d(TAG, "Период: ${period.displayName}")
        Log.d(TAG, "URL: $url")

        return url
    }
}