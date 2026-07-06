package com.example.carsrecommendationapp
import com.example.carsrecommendationapp.presentation.ui.screen.BrandSelectionScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carsrecommendationapp.presentation.ui.screen.NameInputScreen
import com.example.carsrecommendationapp.presentation.ui.screen.WelcomeScreen
import com.example.carsrecommendationapp.presentation.ui.screen.BodyAndFuelScreen
import com.example.carsrecommendationapp.presentation.ui.screen.BudgetAndDetailsScreen
import com.example.carsrecommendationapp.presentation.ui.screen.ResultsScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carsrecommendationapp.presentation.ui.viewmodel.OnboardingViewModel
import com.example.carsrecommendationapp.presentation.ui.screen.FuelSelectionScreen
import com.example.carsrecommendationapp.presentation.ui.screen.DrivingHabitsScreen
import com.example.carsrecommendationapp.presentation.ui.screen.VehicleDetailsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val onboardingViewModel: OnboardingViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = "welcome"
            ) {
                composable("welcome") {
                    WelcomeScreen(
                        onStartClick = {
                            navController.navigate("name_input")
                        }
                    )
                }

                composable("name_input") {
                    NameInputScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate("brands")
                        }
                    )
                }
                composable("brands") {
                    BrandSelectionScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate("body_fuel")
                        }
                    )
                }
                composable("body_fuel") {
                    BodyAndFuelScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate("fuel_selection")
                        }
                    )
                }

                composable("fuel_selection") {
                    FuelSelectionScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate("budget_details")
                        }
                    )
                }
                composable("budget_details") {
                    BudgetAndDetailsScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate("driving_habits")
                        }
                    )
                }

                composable("driving_habits") {
                    DrivingHabitsScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onFindCarClick = {
                            navController.navigate("results")
                        }
                    )
                }

                composable("results") {
                    ResultsScreen(
                        onboardingViewModel = onboardingViewModel,
                        onCarClick = {
                            navController.navigate("vehicle_details")
                        }
                    )
                }
                composable("vehicle_details") {
                    VehicleDetailsScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}