package com.example.carsrecommendationapp.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.BackButton
import com.example.carsrecommendationapp.presentation.ui.components.VehicleSpecCard
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.carsrecommendationapp.presentation.ui.viewmodel.OnboardingViewModel
import androidx.compose.ui.res.stringResource


@Composable
fun VehicleDetailsScreen(
    onboardingViewModel: OnboardingViewModel,
    onBackClick: () -> Unit
) {

    val selectedCar by onboardingViewModel.selectedRecommendation.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        colorResource(R.color.pure_black),
                        colorResource(R.color.deep_dark_blue),
                        colorResource(R.color.dark_blue_gradient)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
                    .background(
                        colorResource(R.color.dark_orange_brown)
                    )
            ) {

                Image(
                    painter = painterResource(R.drawable.car_result_orange),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    contentScale = ContentScale.Fit
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 24.dp,
                            vertical = 56.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    BackButton(
                        onClick = onBackClick
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(
                                    colorResource(R.color.card_dark),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "↗",
                                color = colorResource(R.color.white)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(
                                    colorResource(R.color.card_dark),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "♡",
                                color = colorResource(R.color.white)
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "${selectedCar?.marka ?: ""} ${selectedCar?.model ?: ""}",
                    color = colorResource(R.color.white),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = stringResource(
                        R.string.vehicle_subtitle,
                        selectedCar?.godiste?.toString() ?: "-",
                        formatVehicleMileage(selectedCar?.kilometraza)
                    ),
                    color = colorResource(R.color.light_gray),
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = stringResource(R.string.vehicle_price_label),
                            color = colorResource(R.color.light_gray),
                            fontSize = 14.sp
                        )

                        Text(
                            text = formatVehiclePrice(selectedCar?.cena),
                            color = colorResource(R.color.orange),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(
                                colorResource(R.color.orange),
                                RoundedCornerShape(50.dp)
                            )
                            .padding(
                                horizontal = 18.dp,
                                vertical = 10.dp
                            )
                    ) {
                        Text(
                            text = stringResource(
                                R.string.match_percentage,
                                selectedCar?.skor ?: 0
                            ),
                            color = colorResource(R.color.white),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = stringResource(R.string.vehicle_specs_title),
                    color = colorResource(R.color.white),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.height(190.dp)
                ) {
                    item {
                        VehicleSpecCard(
                            label = stringResource(R.string.fuel_label),
                            value = selectedCar?.gorivo
                                ?: stringResource(R.string.not_available)
                        )
                    }

                    item {
                        VehicleSpecCard(
                            label = stringResource(R.string.body_type_label),
                            value = selectedCar?.karoserija
                                ?: stringResource(R.string.not_available)
                        )
                    }

                    item {
                        VehicleSpecCard(
                            label = stringResource(R.string.transmission_label),
                            value = selectedCar?.menjac
                                ?: stringResource(R.string.not_available)
                        )
                    }

                    item {
                        VehicleSpecCard(
                            label = stringResource(R.string.drive_type_label),
                            value = selectedCar?.pogon
                                ?: stringResource(R.string.not_available)
                        )
                    }
                }

            }
        }
    }
}
fun formatVehiclePrice(price: Int?): String {
    return "€ " + (price ?: 0).toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
}

fun formatVehicleMileage(mileage: Int?): String {
    return (mileage ?: 0).toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
}