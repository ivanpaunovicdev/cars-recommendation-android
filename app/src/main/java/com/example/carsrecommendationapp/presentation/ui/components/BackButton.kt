package com.example.carsrecommendationapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.theme.Dimens

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(Dimens.AvatarSize)
            .background(
                color = colorResource(R.color.card_dark),
                shape = RoundedCornerShape(Dimens.Small)
            )
            .border(
                width = Dimens.Tiny,
                color = colorResource(R.color.light_gray),
                shape = RoundedCornerShape(Dimens.Small)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back),
            contentDescription = "Back",
            tint = colorResource(R.color.white),
            modifier = Modifier.size(Dimens.LargePlus)
        )
    }
}