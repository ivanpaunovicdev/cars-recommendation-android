package com.example.carsrecommendationapp.presentation.ui.screen



import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.R
import com.example.carsrecommendationapp.presentation.ui.components.BackButton
import com.example.carsrecommendationapp.presentation.ui.components.PrimaryButton
import com.example.carsrecommendationapp.presentation.ui.theme.Dimens
import com.example.carsrecommendationapp.presentation.viewmodel.OnboardingViewModel

@Composable
fun NameInputScreen(
    onboardingViewModel: OnboardingViewModel,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {


    val savedUserName by onboardingViewModel.userName.collectAsState()

    var name by rememberSaveable {
        mutableStateOf(savedUserName)
    }

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
            .padding(horizontal = Dimens.XXLarge)
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
                    .size(Dimens.BrandImageSize)
                    .border(Dimens.Tiny, colorResource(R.color.orange), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_person),
                    contentDescription = stringResource(R.string.user_icon_description),
                    tint = colorResource(R.color.orange),
                    modifier = Modifier.size(Dimens.ScreenTop)
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
                    .height(Dimens.IconSize)
                    .border(
                        width = Dimens.Divider,
                        color = colorResource(R.color.orange),
                        shape = RoundedCornerShape(Dimens.Large)
                    )
                    .padding(horizontal = Dimens.Large),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_person),
                        contentDescription = stringResource(R.string.name_icon_description),
                        tint = colorResource(R.color.orange),
                        modifier = Modifier.size(Dimens.CornerRadius)
                    )

                    Spacer(modifier = Modifier.width(Dimens.MediumSmall))

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
                    contentDescription = stringResource(R.string.info_icon_description),
                    tint = colorResource(R.color.light_gray),
                    modifier = Modifier.size(Dimens.MediumSmall)
                )

                Spacer(modifier = Modifier.width(Dimens.ExtraSmall))

                Text(
                    text = stringResource(R.string.name_privacy_info),
                    color = colorResource(R.color.light_gray),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = stringResource(R.string.continue_text_simple),
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