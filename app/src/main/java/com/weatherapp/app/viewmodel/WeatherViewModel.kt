package com.weatherapp.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.app.data.City
import com.weatherapp.app.data.CityRepository
import com.weatherapp.app.data.ForecastPeriod
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: CityRepository
) : ViewModel() {

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()

    private val _searchResults = MutableStateFlow<List<City>>(emptyList())
    val searchResults: StateFlow<List<City>> = _searchResults.asStateFlow()

    private val _selectedCity = MutableStateFlow<City?>(null)
    val selectedCity: StateFlow<City?> = _selectedCity.asStateFlow()

    private val _selectedPeriod = MutableStateFlow(ForecastPeriod.TODAY)
    val selectedPeriod: StateFlow<ForecastPeriod> = _selectedPeriod.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()

    init {
        loadCities()
    }

    private fun loadCities() {
        viewModelScope.launch {
            repository.loadCities().collect { cities ->
                _cities.value = cities
            }
        }
    }

    fun searchCities(query: String) {
        viewModelScope.launch {
            if (query.length < 2) {
                _searchResults.value = emptyList()
                _isSearching.value = false
                return@launch
            }

            _isSearching.value = true
            delay(300)

            val results = repository.searchCities(query, _cities.value)
            _searchResults.value = results
            _isSearching.value = false
        }
    }

    fun clearSearch() {
        _searchResults.value = emptyList()
    }

    fun selectCity(city: City?) {
        _selectedCity.value = city
        if (city != null) {
            _searchResults.value = emptyList()
        }
    }

    fun selectPeriod(period: ForecastPeriod) {
        _selectedPeriod.value = period
    }
}