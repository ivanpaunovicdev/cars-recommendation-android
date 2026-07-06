package com.example.carsrecommendationapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R

@Composable
fun SearchBar(
    onFilterClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .background(
                    color = colorResource(R.color.card_dark),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(android.R.drawable.ic_menu_search),
                contentDescription = null,
                tint = colorResource(R.color.light_gray)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Pretraži model...",
                color = colorResource(R.color.light_gray),
                fontSize = 15.sp
            )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = colorResource(R.color.orange),
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable {
                    onFilterClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(android.R.drawable.ic_menu_sort_by_size),
                contentDescription = null,
                tint = colorResource(R.color.white)
            )
        }
    }
}