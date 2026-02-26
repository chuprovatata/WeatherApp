package com.weatherapp.app.data

enum class ForecastPeriod(val displayName: String, val yandexPath: String) {
    TODAY("Сегодня", "details/today"),
    TOMORROW("Завтра", "details/tomorrow"),
    THREE_DAYS("На 3 дня", "details/3-day-weather"),
    FIVE_DAYS("На 5 дней", "details/5-day-weather"),
    WEEK("На неделю", "details/7-day-weather"),
    TWO_WEEKS("На 2 недели", "details/14-day-weather"),
}