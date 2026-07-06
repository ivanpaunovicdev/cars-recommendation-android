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
            Text(text = "${car.marka} ${car.model}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Cena: ${car.cena}€")
            Text(text = "Gorivo: ${car.gorivo}")
            Text(text = "Godište: ${car.godiste}")
            Text(text = "Kilometraža: ${car.kilometraza} km")
            Text(text = "Menjač: ${car.menjac}")
            Text(text = "Karoserija: ${car.karoserija}")
            Text(text = "Pogon: ${car.pogon}")
            Text(text = "Skor: ${car.skor}")
        }
    }
}