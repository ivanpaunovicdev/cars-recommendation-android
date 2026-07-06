package com.example.carsrecommendationapp.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.BackButton
import com.example.carsrecommendationapp.presentation.ui.components.DrivingHabitCard
import com.example.carsrecommendationapp.presentation.ui.components.DrivingRouteCard
import com.example.carsrecommendationapp.presentation.ui.components.PrimaryButton
import com.example.carsrecommendationapp.presentation.ui.components.StepProgressIndicator
import com.example.carsrecommendationapp.presentation.ui.viewmodel.DailyRoutesViewModel
import com.example.carsrecommendationapp.presentation.ui.viewmodel.DrivingPhilosophiesViewModel
import com.example.carsrecommendationapp.presentation.ui.viewmodel.DrivingTerrainsViewModel
import com.example.carsrecommendationapp.presentation.ui.viewmodel.OnboardingViewModel

@Composable
fun DrivingHabitsScreen(
    onboardingViewModel: OnboardingViewModel,
    onBackClick: () -> Unit,
    onFindCarClick: () -> Unit
) {
    val dailyRoutesViewModel: DailyRoutesViewModel = viewModel()
    val drivingTerrainsViewModel: DrivingTerrainsViewModel = viewModel()
    val drivingPhilosophiesViewModel: DrivingPhilosophiesViewModel = viewModel()

    val dailyRoutes by dailyRoutesViewModel.dailyRoutes.collectAsState()
    val drivingTerrains by drivingTerrainsViewModel.drivingTerrains.collectAsState()
    val drivingPhilosophies by drivingPhilosophiesViewModel.drivingPhilosophies.collectAsState()

    var selectedDailyRoute by remember { mutableStateOf("") }
    var selectedDrivingTerrain by remember { mutableStateOf("") }
    var selectedDrivingPhilosophy by remember { mutableStateOf("") }

    BoxWithConstraints(
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
            .padding(horizontal = 28.dp)
    ) {
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
        ) {
            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            Row(modifier = Modifier.fillMaxWidth()) {
                BackButton(onClick = onBackClick)
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            StepProgressIndicator(
                currentStep = 5,
                totalSteps = 5
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "KORAK 5 OD 5",
                    color = colorResource(R.color.orange),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                Text(
                    text = "Tvoje navike vožnje",
                    color = colorResource(R.color.white),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                Text(
                    text = "Poslednji korak - reci nam nešto o tome kako voziš.",
                    color = colorResource(R.color.light_gray),
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.035f))

                Text(
                    text = "DNEVNA RUTA",
                    color = colorResource(R.color.white),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(176.dp)
                ) {
                    items(dailyRoutes) { route ->
                        val name = route.naziv ?: ""
                        val subtitle = route.opis ?: ""

                        DrivingRouteCard(
                            title = name,
                            subtitle = subtitle,
                            isSelected = selectedDailyRoute == name,
                            onClick = {
                                selectedDailyRoute = name
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(screenHeight * 0.035f))

                Text(
                    text = "PRIMARNI TEREN VOŽNJE",
                    color = colorResource(R.color.white),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    drivingTerrains.chunked(2).forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            rowItems.forEach { terrain ->
                                val name = terrain.naziv ?: ""
                                val description = terrain.opis ?: ""

                                Box(modifier = Modifier.weight(1f)) {
                                    DrivingHabitCard(
                                        title = name,
                                        description = description,
                                        isSelected = selectedDrivingTerrain == name,
                                        onClick = {
                                            selectedDrivingTerrain = name
                                        }
                                    )
                                }
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                Text(
                    text = "MOJA FILOZOFIJA VOŽNJE",
                    color = colorResource(R.color.white),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    drivingPhilosophies.chunked(2).forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            rowItems.forEach { philosophy ->
                                val name = philosophy.naziv ?: ""
                                val description = philosophy.opis ?: ""

                                Box(modifier = Modifier.weight(1f)) {
                                    DrivingHabitCard(
                                        title = name,
                                        description = description,
                                        isSelected = selectedDrivingPhilosophy == name,
                                        onClick = {
                                            selectedDrivingPhilosophy = name
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(screenHeight * 0.025f))
            }

            PrimaryButton(
                text = "Pronađi auto za mene",
                enabled = selectedDailyRoute.isNotBlank() &&
                        selectedDrivingTerrain.isNotBlank() &&
                        selectedDrivingPhilosophy.isNotBlank(),
                onClick = {
                    onboardingViewModel.updateDailyRoute(selectedDailyRoute)
                    onboardingViewModel.updateDrivingTerrain(selectedDrivingTerrain)
                    onboardingViewModel.updateDrivingPhilosophy(selectedDrivingPhilosophy)
                    onFindCarClick()
                }
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))
        }
    }
}