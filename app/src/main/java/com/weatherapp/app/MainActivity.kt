package com.weatherapp.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.weatherapp.app.data.CityRepository
import com.weatherapp.app.ui.WeatherScreen
import com.weatherapp.app.ui.theme.WeatherappTheme
import com.weatherapp.app.viewmodel.WeatherViewModel
import com.weatherapp.app.viewmodel.WeatherViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = CityRepository(applicationContext)
        val viewModelFactory = WeatherViewModelFactory(repository)

        setContent {
            WeatherappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherScreen(
                        onOpenBrowser = { url ->
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                startActivity(intent)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        },
                        viewModel = ViewModelProvider(
                            viewModelStore,
                            viewModelFactory
                        )[WeatherViewModel::class.java]
                    )
                }
            }
        }
    }
}