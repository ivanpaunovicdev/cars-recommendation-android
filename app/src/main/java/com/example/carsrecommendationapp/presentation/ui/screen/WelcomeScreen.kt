package com.example.carsrecommendationapp.presentation.ui.screen
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.carsrecommendationapp.R
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsrecommendationapp.presentation.ui.components.PrimaryButton
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.navigationBarsPadding
import com.example.carsrecommendationapp.presentation.ui.theme.Dimens

@Composable
fun WelcomeScreen(
    onStartClick: () -> Unit
) {
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

            Spacer(modifier = Modifier.height(screenHeight * 0.18f))

            Box(
                modifier = Modifier
                    .size(screenHeight * 0.13f)
                    .rotate(-7f)
                    .background(
                        colorResource(R.color.orange),
                        RoundedCornerShape(Dimens.ScreenBottom)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_car_logo),
                    contentDescription = stringResource(R.string.car_logo_description),
                    modifier = Modifier.size(screenHeight * 0.08f)
                )
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.045f))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = colorResource(R.color.white),
                            fontWeight = FontWeight.Black
                        )
                    ) {
                        append(stringResource(R.string.drive))
                    }
                    withStyle(
                        SpanStyle(
                            color = colorResource(R.color.orange),
                            fontWeight = FontWeight.Black
                        )
                    ) {
                        append(stringResource(R.string.match))
                    }
                },
                fontSize = 54.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.025f))

            Text(
                text = stringResource(R.string.find_perfect_car),
                color = colorResource(R.color.light_gray),
                fontSize = 18.sp,
                lineHeight = 30.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = stringResource(R.string.start),
                onClick = onStartClick
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Text(
                text = stringResource(R.string.already_have_account),
                color = colorResource(R.color.light_gray),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.04f))
        }
    }
}

