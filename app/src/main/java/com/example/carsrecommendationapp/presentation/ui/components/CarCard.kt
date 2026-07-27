package com.example.carsrecommendationapp.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.carsrecommendationapp.domain.Recommendation

@Composable
fun CarCard(car: Recommendation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "${car.brand} ${car.model}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Cena: ${car.price}€")
            Text(text = "Gorivo: ${car.fuel}")
            Text(text = "Godište: ${car.year}")
            Text(text = "Kilometraža: ${car.mileage} km")
            Text(text = "Menjač: ${car.transmission}")
            Text(text = "Karoserija: ${car.bodyType}")
            Text(text = "Pogon: ${car.driveType}")
            Text(text = "Skor: ${car.score}")
        }
    }
}