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
import com.example.carsrecommendationapp.presentation.ui.theme.Dimens

@Composable
fun ActiveFilterChip(
    text: String
) {
    Row(
        modifier = Modifier
            .background(
                color = colorResource(R.color.dark_orange_brown),
                shape = RoundedCornerShape(Dimens.ChipCornerRadius)
            )
            .border(
                width = Dimens.Tiny,
                color = colorResource(R.color.orange),
                shape = RoundedCornerShape(Dimens.ChipCornerRadius)
            )
            .padding(
                horizontal = Dimens.CompactPlus,
                vertical = Dimens.Small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = text,
            color = colorResource(R.color.orange),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.width(Dimens.ExtraSmall))

        Text(
            text = "×",
            color = colorResource(R.color.orange),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}