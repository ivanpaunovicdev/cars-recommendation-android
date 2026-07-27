package com.example.carsrecommendationapp.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.BackButton
import com.example.carsrecommendationapp.presentation.ui.components.FuelTypeCard
import com.example.carsrecommendationapp.presentation.ui.components.PrimaryButton
import com.example.carsrecommendationapp.presentation.ui.components.StepProgressIndicator
import com.example.carsrecommendationapp.presentation.ui.viewmodel.FuelsViewModel
import com.example.carsrecommendationapp.presentation.ui.viewmodel.OnboardingViewModel

@Composable
fun FuelSelectionScreen(
    onboardingViewModel: OnboardingViewModel,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    val fuelsViewModel: FuelsViewModel = hiltViewModel()
    val fuels by fuelsViewModel.fuels.collectAsState()

    val isLoading by fuelsViewModel.isLoading.collectAsState()
    val errorMessage by fuelsViewModel.errorMessage.collectAsState()

    val savedSelectedFuels by onboardingViewModel.selectedFuels.collectAsState()

    var selectedFuels by remember(savedSelectedFuels) {
        mutableStateOf(savedSelectedFuels)
    }

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

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))

            StepProgressIndicator(
                currentStep = 3,
                totalSteps = 5
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))

            Text(
                text = stringResource(R.string.step_3_of_5),
                color = colorResource(R.color.orange),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.012f))

            Text(
                text = stringResource(R.string.fuel_type_title),
                color = colorResource(R.color.white),
                fontSize = 34.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.012f))

            Text(
                text = stringResource(R.string.fuel_type_description),
                color = colorResource(R.color.light_gray),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.loading),
                            color = colorResource(R.color.white)
                        )
                    }
                }

                errorMessage != null -> {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.unable_to_load_data),
                            color = colorResource(R.color.orange)
                        )
                    }
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(fuels.take(4)) { fuel ->
                            val name = fuel.naziv ?: ""

                            FuelTypeCard(
                                title = name,
                                description = fuelDescription(name),
                                icon = fuelIcon(name),
                                isSelected = selectedFuels.contains(name),
                                onClick = {
                                    selectedFuels =
                                        if (selectedFuels.contains(name)) {
                                            selectedFuels - name
                                        } else {
                                            selectedFuels + name
                                        }
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.025f))

            PrimaryButton(
                text = stringResource(R.string.continue_text_simple),
                enabled = selectedFuels.isNotEmpty(),
                onClick = {
                    onboardingViewModel.updateFuels(selectedFuels)
                    onContinueClick()
                }
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))
        }
    }
}

fun fuelIcon(name: String): String {
    return when (name.lowercase()) {
        "benzin" -> "⛽"
        "dizel" -> "◷"
        "hibrid" -> "⚡"
        "električni", "elektricni" -> "▯"
        else -> "⛽"
    }
}

fun fuelDescription(name: String): String {
    return when (name.lowercase()) {
        "benzin" -> "Dostupan svuda, brz start,\nširoka ponuda"
        "dizel" -> "Efikasniji na dugim\nrelacijama, jači obrtni"
        "hibrid" -> "Manja potrošnja u gradu,\nkombinovan pogon"
        "električni", "elektricni" -> "Nulta emisija, niski\ntroškovi održavanja"
        else -> "Pouzdan izbor za svakodnevnu vožnju"
    }
}