package com.example.carsrecommendationapp.presentation.ui.screen
import com.example.carsrecommendationapp.util.formatMileage
import com.example.carsrecommendationapp.util.formatPrice
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.carsrecommendationapp.presentation.ui.viewmodel.OnboardingViewModel
import com.example.carsrecommendationapp.presentation.ui.viewmodel.RecommendationViewModel
import androidx.compose.material3.CircularProgressIndicator


@Composable
fun ResultsScreen(
    onboardingViewModel: OnboardingViewModel,
    onCarClick: () -> Unit
) {

    val recommendationViewModel: RecommendationViewModel = hiltViewModel()
    val recommendations by recommendationViewModel.recommendations.collectAsState()
    val isLoading by recommendationViewModel.isLoading.collectAsState()
    val errorMessage by recommendationViewModel.errorMessage.collectAsState()
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
    val favoriteCars = remember { mutableStateListOf<String>() }

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
            brands = selectedBrands.toList(),
            bodyTypes = selectedBodyTypes.toList(),
            fuels = selectedFuels.toList(),
            transmission = transmission,
            dailyRoute = selectedDailyRoute,
            drivingTerrain = selectedDrivingTerrain,
            drivingPhilosophy = selectedDrivingPhilosophy
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
                            text = stringResource(
                                R.string.hello_user,
                                userName.ifBlank { stringResource(R.string.default_user) }
                            ),
                            color = colorResource(R.color.light_gray),
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = stringResource(R.string.your_recommendations),
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

                    ActiveFilterChip(stringResource(R.string.budget_filter, budgetMax))

                    ActiveFilterChip(stringResource(R.string.year_filter, minYear))

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
                        text = stringResource(R.string.results_count, recommendations.size),
                        color = colorResource(R.color.orange),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    SortChip()
                }

                Spacer(modifier = Modifier.height(18.dp))
            }

            when {
                isLoading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = colorResource(R.color.orange)
                            )
                        }
                    }
                }

                errorMessage != null -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.unable_to_load_recommendations),
                                color = colorResource(R.color.orange)
                            )
                        }
                    }
                }

                else -> {
                    items(recommendations.size) { index ->
                        val car = recommendations[index]
                        val carId = "${car.brand}-${car.model}-${car.year}"

                        CarResultCard(
                            title = "${car.brand} ${car.model}",
                            subtitle = "${car.brand} · ${car.year} · ${formatMileage(car.mileage)} km",
                            price = formatPrice(car.price),
                            matchPercent = car.score,
                            fuel = car.fuel,
                            transmission = car.transmission,
                            driveType = car.driveType,
                            isFavorite = carId in favoriteCars,
                            onFavoriteClick = {
                                if (carId in favoriteCars) {
                                    favoriteCars.remove(carId)
                                } else {
                                    favoriteCars.add(carId)
                                }
                            },
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
    }
}





