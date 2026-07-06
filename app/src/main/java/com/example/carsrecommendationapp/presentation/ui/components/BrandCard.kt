package com.example.carsrecommendationapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R

@Composable
fun BrandCard(
    brand: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(118.dp)
            .background(
                color = if (isSelected)
                    colorResource(R.color.dark_orange_brown)
                else
                    colorResource(R.color.card_dark),
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected)
                    colorResource(R.color.orange)
                else
                    colorResource(R.color.card_dark),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = if (isSelected)
                            colorResource(R.color.orange)
                        else
                            colorResource(R.color.circle_dark),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getBrandInitials(brand),
                    color = colorResource(R.color.white),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = brand,
                color = colorResource(R.color.white),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private fun getBrandInitials(brand: String): String {
    return when (brand) {
        "Mercedes" -> "MB"
        "Volkswagen" -> "VW"
        "Škoda" -> "Š"
        "BMW" -> "BMW"
        else -> brand.first().uppercase()
    }
}