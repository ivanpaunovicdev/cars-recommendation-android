package com.example.carsrecommendationapp.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.ActiveFilterChip
import com.example.carsrecommendationapp.presentation.ui.components.SearchBar
import androidx.compose.ui.Alignment
import com.example.carsrecommendationapp.presentation.ui.components.AvatarCircle
import com.example.carsrecommendationapp.presentation.ui.components.CarResultCard
import com.example.carsrecommendationapp.presentation.ui.components.SortChip
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.carsrecommendationapp.presentation.ui.viewmodel.OnboardingViewModel
import com.example.carsrecommendationapp.presentation.ui.viewmodel.RecommendationViewModel


@Composable
fun ResultsScreen(
    onboardingViewModel: OnboardingViewModel,
    onCarClick: () -> Unit
) {

    val recommendationViewModel: RecommendationViewModel = viewModel()
    val recommendations by recommendationViewModel.recommendations.collectAsState()
    val selectedBrands by onboardingViewModel.selectedBrands.collectAsState()
    val selectedBodyTypes by onboardingViewModel.selectedBodyTypes.collectAsState()
    val selectedFuels by onboardingViewModel.selectedFuels.collectAsState()
    val budgetMax by onboardingViewModel.budgetMax.collectAsState()
    val minYear by onboardingViewModel.minYear.collectAsState()
    val transmission by onboardingViewModel.transmission.collectAsState()
    val selectedDailyRoute by onboardingViewModel.selectedDailyRoute.collectAsState()
    val selectedDrivingTerrain by onboardingViewModel.selectedDrivingTerrain.collectAsState()
    val selectedDrivingPhilosophy by onboardingViewModel.selectedDrivingPhilosophy.collectAsState()
    val userName by onboardingViewModel.userName.collectAsState()

    LaunchedEffect(
        selectedBrands,
        selectedBodyTypes,
        selectedFuels,
        budgetMax,
        minYear,
        transmission,
        selectedDailyRoute,
        selectedDrivingTerrain,
        selectedDrivingPhilosophy
    ) {
        recommendationViewModel.loadRecommendations(
            budgetMin = 0,
            budgetMax = budgetMax,
            minYear = minYear,
            brand = selectedBrands.firstOrNull() ?: "",
            bodyType = selectedBodyTypes.firstOrNull() ?: "",
            fuel = selectedFuels.firstOrNull() ?: "",
            transmission = transmission
        )
    }

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
            .padding(horizontal = 24.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = 54.dp,
                bottom = 32.dp
            )
        ) {

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {

                    Column {

                        Text(
                            text = "Zdravo, ${userName.ifBlank { "Korisniče" }}",
                            color = colorResource(R.color.light_gray),
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Tvoje preporuke",
                            color = colorResource(R.color.white),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    AvatarCircle(
                        initial = userName.firstOrNull()?.uppercase() ?: "K"
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                SearchBar()

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (selectedBrands.isNotEmpty()) {
                        ActiveFilterChip(selectedBrands.joinToString(", "))
                    }

                    if (selectedBodyTypes.isNotEmpty()) {
                        ActiveFilterChip(selectedBodyTypes.joinToString(", "))
                    }

                    if (selectedFuels.isNotEmpty()) {
                        ActiveFilterChip(selectedFuels.joinToString(", "))
                    }

                    ActiveFilterChip("do €$budgetMax")

                    ActiveFilterChip("$minYear+")

                    if (transmission.isNotBlank()) {
                        ActiveFilterChip(transmission)
                    }

                    if (selectedDailyRoute.isNotBlank()) {
                        ActiveFilterChip(selectedDailyRoute)
                    }

                    if (selectedDrivingTerrain.isNotBlank()) {
                        ActiveFilterChip(selectedDrivingTerrain)
                    }

                    if (selectedDrivingPhilosophy.isNotBlank()) {
                        ActiveFilterChip(selectedDrivingPhilosophy)
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${recommendations.size} rezultata",
                        color = colorResource(R.color.orange),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    SortChip()
                }

                Spacer(modifier = Modifier.height(18.dp))
            }

            items(recommendations.size) { index ->
                val car = recommendations[index]

                CarResultCard(
                    title = "${car.marka ?: ""} ${car.model ?: ""}",
                    subtitle = "${car.marka ?: ""} · ${car.godiste ?: "-"} · ${formatMileage(car.kilometraza)} km",
                    price = formatPrice(car.cena),
                    matchPercent = car.skor ?: 0,
                    fuel = car.gorivo ?: "N/A",
                    transmission = car.menjac ?: "N/A",
                    power = car.pogon ?: "N/A",
                    isFavorite = index == 0,
                    onFavoriteClick = { },
                    onClick = {
                        onboardingViewModel.updateSelectedRecommendation(car)
                        onCarClick()
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))
            }

        }
    }
}

fun formatPrice(price: Int?): String {
    return "€ " + (price ?: 0).toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
}

fun formatMileage(mileage: Int?): String {
    return (mileage ?: 0).toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
}



