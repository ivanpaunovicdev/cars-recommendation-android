package com.example.carsrecommendationapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R

@Composable
fun CarResultCard(
    title: String,
    subtitle: String,
    price: String,
    matchPercent: Int,
    fuel: String,
    transmission: String,
    driveType: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(colorResource(R.color.card_dark))
            .clickable { onClick() }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(colorResource(R.color.dark_orange_brown))
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = colorResource(R.color.orange),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 7.dp)
                    ) {
                        Text(
                            text = "★ ${matchPercent}% match",
                            color = colorResource(R.color.white),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .background(
                                colorResource(R.color.circle_dark),
                                CircleShape
                            )
                            .clickable {
                                onFavoriteClick()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (isFavorite) "♥" else "♡",
                            color = if (isFavorite)
                                colorResource(R.color.orange)
                            else
                                colorResource(R.color.white),
                            fontSize = 18.sp
                        )
                    }
                }

                Image(
                    painter = painterResource(R.drawable.car_result_orange),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(300.dp)
                        .height(150.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = title,
                            color = colorResource(R.color.white),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = subtitle,
                            color = colorResource(R.color.light_gray),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = price,
                        color = colorResource(R.color.orange),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colorResource(R.color.circle_dark))
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CarInfoItem(icon = "⚡", text = fuel)
                    CarInfoItem(icon = "↗", text = transmission)
                    CarInfoItem(icon = "◉", text = driveType)
                }
            }
        }
    }
}

@Composable
fun CarInfoItem(
    icon: String,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = icon,
            color = colorResource(R.color.light_gray),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            color = colorResource(R.color.light_gray),
            fontSize = 12.sp
        )
    }
}