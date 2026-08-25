package com.example.carsrecommendationapp.presentation.ui.screen

import com.example.carsrecommendationapp.util.apiToUiTransmission
import com.example.carsrecommendationapp.util.uiToApiTransmission
import com.example.carsrecommendationapp.util.formatPrice
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.BackButton
import com.example.carsrecommendationapp.presentation.ui.components.OptionChip
import com.example.carsrecommendationapp.presentation.ui.components.PrimaryButton
import com.example.carsrecommendationapp.presentation.ui.components.StepProgressIndicator
import com.example.carsrecommendationapp.presentation.ui.components.TransmissionOptionChip
import com.example.carsrecommendationapp.presentation.ui.theme.Dimens
import com.example.carsrecommendationapp.presentation.viewmodel.OnboardingViewModel
import com.example.carsrecommendationapp.presentation.viewmodel.DriveTypesViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetAndDetailsScreen(
    onboardingViewModel: OnboardingViewModel,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {


    val savedBudgetMax by onboardingViewModel.budgetMax.collectAsState()
    val savedMinYear by onboardingViewModel.minYear.collectAsState()
    val savedTransmission by onboardingViewModel.transmission.collectAsState()
    val driveTypesViewModel: DriveTypesViewModel = hiltViewModel()
    val driveTypes by driveTypesViewModel.driveTypes.collectAsState()

    val savedDriveType by onboardingViewModel.driveType.collectAsState()

    var price by rememberSaveable(savedBudgetMax) {
        mutableStateOf(savedBudgetMax.toFloat())
    }

    var selectedYear by rememberSaveable(savedMinYear) {
        mutableStateOf("${savedMinYear}+")
    }

    var selectedTransmission by rememberSaveable(savedTransmission) {
        mutableStateOf(apiToUiTransmission(savedTransmission))
    }

    var selectedDriveType by rememberSaveable(savedDriveType) {
        mutableStateOf(savedDriveType)
    }

    val years = listOf(
        "2018+",
        "2019+",
        "2020+",
        "2021+",
        "2022+",
        "2023+",
        "2024+",
        "2025+",
        "2026+"
    )
    val transmissions = listOf("Automatik", "Manuelni", "Svejedno")
    val driveTypeOptions = driveTypes.mapNotNull { it.naziv }

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
                currentStep = 4,
                totalSteps = 5
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.step_4_of_5),
                    color = colorResource(R.color.orange),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                Text(
                    text = stringResource(R.string.budget_details_title),
                    color = colorResource(R.color.white),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                Text(
                    text = stringResource(R.string.budget_details_description),
                    color = colorResource(R.color.light_gray),
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.035f))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            color = colorResource(R.color.card_dark),
                            shape = RoundedCornerShape(Dimens.ExtraLarge)
                        )
                        .border(
                            width = 1.dp,
                            color = colorResource(R.color.card_dark),
                            shape = RoundedCornerShape(Dimens.ExtraLarge)
                        )
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.price_range),
                            color = colorResource(R.color.light_gray),
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(Dimens.Small))

                        Text(
                            text = formatPrice(price.toInt()),
                            color = colorResource(R.color.orange),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = stringResource(R.string.monthly_estimate),
                            color = colorResource(R.color.light_gray),
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(screenHeight * 0.025f))

                Slider(
                    value = price,
                    onValueChange = { price = it },
                    valueRange = 0f..100000f,

                    thumb = {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .background(
                                    color = colorResource(R.color.white),
                                    shape = CircleShape
                                )
                                .border(
                                    width = 4.dp,
                                    color = colorResource(R.color.orange),
                                    shape = CircleShape
                                )
                        )
                    },

                    track = { sliderState ->
                        SliderDefaults.Track(
                            sliderState = sliderState,
                            modifier = Modifier.height(4.dp),
                            colors = SliderDefaults.colors(
                                activeTrackColor = colorResource(R.color.orange),
                                inactiveTrackColor = colorResource(R.color.card_dark)
                            ),
                            drawStopIndicator = null,
                            thumbTrackGapSize = 0.dp
                        )
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("€ 0", color = colorResource(R.color.light_gray), fontSize = 13.sp)
                    Text("${formatPrice(100000)}+", color = colorResource(R.color.light_gray), fontSize = 13.sp)
                }

                Spacer(modifier = Modifier.height(screenHeight * 0.025f))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.card_dark),
                            shape = RoundedCornerShape(22.dp)
                        )
                        .padding(Dimens.Medium)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.year),
                                color = colorResource(R.color.white),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = stringResource(R.string.year_range),
                                color = colorResource(R.color.orange),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(Dimens.Small),
                            verticalArrangement = Arrangement.spacedBy(Dimens.Small)
                        ) {
                            years.forEach { year ->
                                OptionChip(
                                    text = year,
                                    isSelected = selectedYear == year,
                                    onClick = { selectedYear = year }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(screenHeight * 0.03f))

                Text(
                    text = stringResource(R.string.transmission),
                    color = colorResource(R.color.white),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.015f))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    transmissions.forEach { transmission ->
                        TransmissionOptionChip(
                            text = transmission,
                            isSelected = selectedTransmission == transmission,
                            onClick = { selectedTransmission = transmission }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(screenHeight * 0.03f))

                Text(
                    text = stringResource(R.string.drive_type),
                    color = colorResource(R.color.white),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.015f))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    driveTypeOptions.forEach { driveType ->
                        TransmissionOptionChip(
                            text = driveType,
                            isSelected = selectedDriveType == driveType,
                            onClick = {
                                selectedDriveType = driveType
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(screenHeight * 0.04f))
            }

            PrimaryButton(
                text = stringResource(R.string.continue_text_simple),
                onClick = {
                    val transmissionForApi = uiToApiTransmission(selectedTransmission)

                    onboardingViewModel.updateBudgetMax(price.toInt())
                    onboardingViewModel.updateMinYear(
                        selectedYear.removeSuffix("+").toInt()
                    )
                    onboardingViewModel.updateTransmission(transmissionForApi)
                    onboardingViewModel.updateDriveType(selectedDriveType)

                    onContinueClick()
                }
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))
        }

    }
}