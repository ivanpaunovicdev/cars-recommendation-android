package com.example.carsrecommendationapp.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
    val fuelsViewModel: FuelsViewModel = viewModel()
    val fuels by fuelsViewModel.fuels.collectAsState()

    var selectedFuels by remember { mutableStateOf(setOf<String>()) }

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
                text = "KORAK 3 OD 5",
                color = colorResource(R.color.orange),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.012f))

            Text(
                text = "Tip goriva",
                color = colorResource(R.color.white),
                fontSize = 34.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.012f))

            Text(
                text = "Izaberi jedan ili više tipova goriva.",
                color = colorResource(R.color.light_gray),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))

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

            Spacer(modifier = Modifier.height(screenHeight * 0.025f))

            PrimaryButton(
                text = "Nastavi",
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