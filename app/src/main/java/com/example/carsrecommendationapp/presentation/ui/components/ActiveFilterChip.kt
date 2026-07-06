package com.example.carsrecommendationapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
fun ActiveFilterChip(
    text: String
) {
    Row(
        modifier = Modifier
            .background(
                color = colorResource(R.color.dark_orange_brown),
                shape = RoundedCornerShape(50.dp)
            )
            .border(
                width = 1.dp,
                color = colorResource(R.color.orange),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = text,
            color = colorResource(R.color.orange),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = "×",
            color = colorResource(R.color.orange),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}