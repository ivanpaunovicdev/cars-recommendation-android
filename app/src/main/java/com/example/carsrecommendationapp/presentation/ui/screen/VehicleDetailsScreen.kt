package com.example.carsrecommendationapp.presentation.ui.screen

import com.example.carsrecommendationapp.util.formatMileage
import com.example.carsrecommendationapp.util.formatPrice
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.BackButton
import com.example.carsrecommendationapp.presentation.ui.components.VehicleSpecCard
import com.example.carsrecommendationapp.presentation.ui.theme.Dimens
import com.example.carsrecommendationapp.presentation.viewmodel.OnboardingViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel


@Composable
fun VehicleDetailsScreen(
    onBackClick: () -> Unit
) {

    val onboardingViewModel: OnboardingViewModel = hiltViewModel()

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
                            horizontal = Dimens.ExtraLarge,
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
                    .padding(horizontal = Dimens.ExtraLarge)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "${selectedCar?.brand ?: ""} ${selectedCar?.model ?: ""}",
                    color = colorResource(R.color.white),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(Dimens.ExtraSmall))

                Text(
                    text = stringResource(
                        R.string.vehicle_subtitle,
                        selectedCar?.year?.toString() ?: "-",
                        formatMileage(selectedCar?.mileage ?: 0)
                    ),
                    color = colorResource(R.color.light_gray),
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(Dimens.ExtraLarge))

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
                            text = formatPrice(selectedCar?.price ?: 0),
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
                                horizontal = Dimens.Large,
                                vertical = 10.dp
                            )
                    ) {
                        Text(
                            text = stringResource(
                                R.string.match_percentage,
                                selectedCar?.score ?: 0
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

                Spacer(modifier = Modifier.height(Dimens.Medium))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.height(190.dp)
                ) {
                    item {
                        VehicleSpecCard(
                            label = stringResource(R.string.fuel_label),
                            value = selectedCar?.fuel
                                ?.takeIf { it.isNotBlank() }
                                ?: stringResource(R.string.not_available)
                        )
                    }

                    item {
                        VehicleSpecCard(
                            label = stringResource(R.string.body_type_label),
                            value = selectedCar?.bodyType
                                ?.takeIf { it.isNotBlank() }
                                ?: stringResource(R.string.not_available)
                        )
                    }

                    item {
                        VehicleSpecCard(
                            label = stringResource(R.string.transmission_label),
                            value = selectedCar?.transmission
                                ?.takeIf { it.isNotBlank() }
                                ?: stringResource(R.string.not_available)
                        )
                    }

                    item {
                        VehicleSpecCard(
                            label = stringResource(R.string.drive_type_label),
                            value = selectedCar?.driveType
                                ?.takeIf { it.isNotBlank() }
                                ?: stringResource(R.string.not_available)
                        )
                    }
                }

            }
        }
    }
}
