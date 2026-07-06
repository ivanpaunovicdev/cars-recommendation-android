package com.example.carsrecommendationapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R
import androidx.compose.ui.Alignment

@Composable
fun BodyTypeCard(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(145.dp)
            .background(
                color = if (isSelected)
                    colorResource(R.color.dark_orange_brown)
                else
                    colorResource(R.color.card_dark),
                shape = RoundedCornerShape(22.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected)
                    colorResource(R.color.orange)
                else
                    colorResource(R.color.circle_dark),
                shape = RoundedCornerShape(22.dp)
            )
            .clickable { onClick() }
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = bodyTypeIcon(title),
                fontSize = 34.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                color = colorResource(R.color.white),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}

fun bodyTypeIcon(title: String): String {
    return when (title.lowercase()) {
        "hečbek", "hecbek" -> "🚗"
        "limuzina" -> "🚘"
        "kupe" -> "🏎️"
        "karavan" -> "🚙"
        "suv" -> "🚙"
        "monovolumen" -> "🚐"
        else -> "🚘"
    }
}