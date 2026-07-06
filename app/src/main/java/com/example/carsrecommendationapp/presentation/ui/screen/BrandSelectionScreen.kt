package com.example.carsrecommendationapp.presentation.ui.screen
import com.example.carsrecommendationapp.presentation.ui.components.BrandCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.carsrecommendationapp.presentation.ui.viewmodel.BrandsViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.BackButton
import com.example.carsrecommendationapp.presentation.ui.components.PrimaryButton
import com.example.carsrecommendationapp.presentation.ui.components.StepProgressIndicator
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.navigationBarsPadding
import com.example.carsrecommendationapp.presentation.ui.viewmodel.OnboardingViewModel

@Composable
fun BrandSelectionScreen(
    onboardingViewModel: OnboardingViewModel,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    val brandsViewModel: BrandsViewModel = viewModel()
    val brands by brandsViewModel.brands.collectAsState()

    var selectedBrands by remember { mutableStateOf(setOf<String>()) }

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

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                BackButton(onClick = onBackClick)
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))

            StepProgressIndicator(
                currentStep = 1,
                totalSteps = 5
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))

            Text(
                text = "KORAK 1 OD 5",
                color = colorResource(R.color.orange),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.018f))

            Text(
                text = "Koji brendovi te\nnajviše interesuju?",
                color = colorResource(R.color.white),
                fontSize = 34.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 38.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.016f))

            Text(
                text = "Izaberi jedan ili više brendova. Možeš preskočiti ovaj korak.",
                color = colorResource(R.color.light_gray),
                fontSize = 16.sp,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(brands) { brand ->
                    val brandName = brand.naziv ?: ""

                    BrandCard(
                        brand = brandName,
                        isSelected = selectedBrands.contains(brandName),
                        onClick = {
                            selectedBrands =
                                if (selectedBrands.contains(brandName)) {
                                    selectedBrands - brandName
                                } else {
                                    selectedBrands + brandName
                                }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            PrimaryButton(
                text = if (selectedBrands.isEmpty()) {
                    "Dalje"
                } else {
                    "Dalje (${selectedBrands.size} izabrano)"
                },
                onClick = {
                    onboardingViewModel.updateBrands(selectedBrands)
                    onContinueClick()
                }
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))
        }
    }
}