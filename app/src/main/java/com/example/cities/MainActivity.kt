package com.example.cities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cities.presentation.CitiesViewModel
import com.example.cities.ui.theme.CitiesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CitiesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Cities(viewModel: CitiesViewModel) {
    val cities = viewModel.sortedCities.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .padding(16.dp)
    ) {
        Text(
            text = "City Search",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "${cities.value?.size} cities",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            val grouped = cities.value?.groupBy { it.name?.first() }
            grouped?.forEach { (initial, cityList) ->
                item {
                    TimelineHeader(letter = initial)
                }
                items(cityList) { city ->
                    CityCard(city = city)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CitiesTheme {
        Cities()
    }
}