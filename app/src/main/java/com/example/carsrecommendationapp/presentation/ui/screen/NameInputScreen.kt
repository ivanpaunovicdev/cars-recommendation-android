package com.example.carsrecommendationapp.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.PrimaryButton
import com.example.carsrecommendationapp.presentation.ui.components.BackButton
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.BoxWithConstraints
import com.example.carsrecommendationapp.presentation.ui.viewmodel.OnboardingViewModel

@Composable
fun NameInputScreen(
    onboardingViewModel: OnboardingViewModel,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        colorResource(R.color.dark_brown),
                        colorResource(R.color.dark_navy),
                        colorResource(R.color.navy_blue)
                    )
                )
            )
            .padding(horizontal = 28.dp)
    ) {
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                BackButton(onClick = onBackClick)
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.09f))

            Box(
                modifier = Modifier
                    .size(118.dp)
                    .border(1.dp, colorResource(R.color.orange), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_person),
                    contentDescription = "User icon",
                    tint = colorResource(R.color.orange),
                    modifier = Modifier.size(54.dp)
                )
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.045f))

            Text(
                text = stringResource(R.string.before_we_start),
                color = colorResource(R.color.white),
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.015f))

            Text(
                text = stringResource(R.string.ask_name),
                color = colorResource(R.color.light_gray),
                fontSize = 13.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.055f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp)
                    .border(
                        width = 1.5.dp,
                        color = colorResource(R.color.orange),
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(horizontal = 18.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_person),
                        contentDescription = "Name icon",
                        tint = colorResource(R.color.orange),
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.your_name),
                            color = colorResource(R.color.light_gray),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )

                        BasicTextField(
                            value = name,
                            onValueChange = { name = it },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                color = colorResource(R.color.white),
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            singleLine = true
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.014f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_info),
                    contentDescription = "Info icon",
                    tint = colorResource(R.color.light_gray),
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = stringResource(R.string.name_privacy_info),
                    color = colorResource(R.color.light_gray),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "Nastavi",
                enabled = name.trim().isNotEmpty(),
                onClick = {
                    onboardingViewModel.updateUserName(name.trim())
                    onContinueClick()
                }
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.035f))
        }
    }
}