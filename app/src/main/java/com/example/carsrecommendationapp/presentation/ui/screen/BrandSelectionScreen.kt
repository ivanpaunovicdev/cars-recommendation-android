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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.BackButton
import com.example.carsrecommendationapp.presentation.ui.components.BrandCard
import com.example.carsrecommendationapp.presentation.ui.components.PrimaryButton
import com.example.carsrecommendationapp.presentation.ui.components.StepProgressIndicator
import com.example.carsrecommendationapp.presentation.ui.viewmodel.BrandsViewModel
import com.example.carsrecommendationapp.presentation.ui.viewmodel.OnboardingViewModel

@Composable
fun BrandSelectionScreen(
    onboardingViewModel: OnboardingViewModel,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    val brandsViewModel: BrandsViewModel = hiltViewModel()
    val brands by brandsViewModel.brands.collectAsState()

    val isLoading by brandsViewModel.isLoading.collectAsState()
    val errorMessage by brandsViewModel.errorMessage.collectAsState()

    val savedSelectedBrands by onboardingViewModel.selectedBrands.collectAsState()

    var selectedBrands by remember(savedSelectedBrands) {
        mutableStateOf(savedSelectedBrands)
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
                text = stringResource(R.string.step_1_of_5),
                color = colorResource(R.color.orange),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.018f))

            Text(
                text = stringResource(R.string.brand_selection_title),
                color = colorResource(R.color.white),
                fontSize = 34.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 38.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.016f))

            Text(
                text = stringResource(R.string.brand_selection_description),
                color = colorResource(R.color.light_gray),
                fontSize = 16.sp,
                lineHeight = 24.sp
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
                            text = errorMessage ?: "",
                            color = colorResource(R.color.orange)
                        )
                    }
                }

                else -> {
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
                                        if (selectedBrands.contains(brandName))
                                            selectedBrands - brandName
                                        else
                                            selectedBrands + brandName
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            PrimaryButton(
                text = if (selectedBrands.isEmpty()) {
                    stringResource(R.string.next)
                } else {
                    stringResource(
                        R.string.next_selected,
                        selectedBrands.size
                    )
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