package com.example.carsrecommendationapp.presentation.ui.screen

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.runtime.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.BackButton
import com.example.carsrecommendationapp.presentation.ui.components.BodyTypeCard
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carsrecommendationapp.presentation.ui.components.PrimaryButton
import com.example.carsrecommendationapp.presentation.ui.components.StepProgressIndicator
import com.example.carsrecommendationapp.presentation.ui.viewmodel.BodyTypesViewModel

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.navigationBarsPadding
import com.example.carsrecommendationapp.presentation.ui.viewmodel.OnboardingViewModel
import androidx.compose.ui.Alignment

@Composable
fun BodyAndFuelScreen(
    onboardingViewModel: OnboardingViewModel,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    val bodyTypesViewModel: BodyTypesViewModel = hiltViewModel()


    val bodyTypes by bodyTypesViewModel.bodyTypes.collectAsState()

    val isLoading by bodyTypesViewModel.isLoading.collectAsState()
    val errorMessage by bodyTypesViewModel.errorMessage.collectAsState()


    val savedSelectedBodyTypes by onboardingViewModel.selectedBodyTypes.collectAsState()

    var selectedBodyTypes by remember(savedSelectedBodyTypes) {
        mutableStateOf(savedSelectedBodyTypes)
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

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            StepProgressIndicator(
                currentStep = 2,
                totalSteps = 5
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Text(
                text = stringResource(R.string.step_2_of_5),
                color = colorResource(R.color.orange),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.012f))

            Text(
                text = stringResource(R.string.body_type_title),
                color = colorResource(R.color.white),
                fontSize = 34.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.01f))

            Text(
                text = stringResource(R.string.body_type_description),
                color = colorResource(R.color.light_gray),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.body_type_section),
                    color = colorResource(R.color.white),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )

//                Text(
//                    text = "višestruki izbor",
//                    color = colorResource(R.color.light_gray),
//                    fontSize = 13.sp
//                )
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.018f))

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
                        items(bodyTypes.take(6)) { bodyType ->
                            val name = bodyType.naziv ?: ""

                            BodyTypeCard(
                                title = name,
                                isSelected = selectedBodyTypes.contains(name),
                                onClick = {
                                    selectedBodyTypes =
                                        if (selectedBodyTypes.contains(name)) {
                                            selectedBodyTypes - name
                                        } else {
                                            selectedBodyTypes + name
                                        }
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.025f))

            Spacer(modifier = Modifier.height(screenHeight * 0.025f))

            PrimaryButton(
                text = stringResource(R.string.continue_text),
                enabled = selectedBodyTypes.isNotEmpty(),
                onClick = {
                    onboardingViewModel.updateBodyTypes(selectedBodyTypes)
                    onContinueClick()
                }
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))
        }
    }
}