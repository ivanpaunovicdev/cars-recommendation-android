package com.example.carsrecommendationapp.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.theme.Dimens

@Composable
fun PrimaryButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.PrimaryButtonHeight),
        shape = RoundedCornerShape(Dimens.Large),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled)
                colorResource(R.color.orange)
            else
                colorResource(R.color.circle_dark),

            contentColor = colorResource(R.color.white),

            disabledContainerColor = colorResource(R.color.circle_dark),
            disabledContentColor = colorResource(R.color.light_gray)
        )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = text,
                color = colorResource(R.color.white),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(Dimens.Small))

            Icon(
                painter = painterResource(R.drawable.baseline_arrow_forward),
                contentDescription = "arrow",
                tint = colorResource(R.color.white)
            )
        }
    }
}