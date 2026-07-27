package com.example.carsrecommendationapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.theme.Dimens

@Composable
fun AvatarCircle(
    initial: String
) {
    Box(
        modifier = Modifier
            .size(Dimens.AvatarSize)
            .background(
                color = colorResource(R.color.orange),
                shape = CircleShape
            )
            .border(
                width = Dimens.ExtraTiny,
                color = colorResource(R.color.card_dark),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial,
            color = colorResource(R.color.white),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}