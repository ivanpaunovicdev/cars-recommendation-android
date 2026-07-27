package com.example.carsrecommendationapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.theme.Dimens

@Composable
fun FuelChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected)
                    colorResource(R.color.dark_orange_brown)
                else
                    colorResource(R.color.card_dark),
                shape = RoundedCornerShape(Dimens.Medium)
            )
            .border(
                width = Dimens.Tiny,
                color = if (isSelected)
                    colorResource(R.color.orange)
                else
                    colorResource(R.color.card_dark),
                shape = RoundedCornerShape(Dimens.Medium)
            )
            .clickable { onClick() }
            .padding(horizontal = Dimens.LargePlus, vertical = Dimens.MediumSmall)
    ) {
        Text(
            text = text,
            color = colorResource(R.color.white),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}