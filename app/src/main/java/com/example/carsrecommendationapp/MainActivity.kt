package com.example.carsrecommendationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carsrecommendationapp.navigation.BodyAndFuelRoute
import com.example.carsrecommendationapp.navigation.BrandsRoute
import com.example.carsrecommendationapp.navigation.BudgetDetailsRoute
import com.example.carsrecommendationapp.navigation.DrivingHabitsRoute
import com.example.carsrecommendationapp.navigation.FuelSelectionRoute
import com.example.carsrecommendationapp.navigation.NameInputRoute
import com.example.carsrecommendationapp.navigation.ResultsRoute
import com.example.carsrecommendationapp.navigation.VehicleDetailsRoute
import com.example.carsrecommendationapp.navigation.WelcomeRoute
import com.example.carsrecommendationapp.presentation.ui.screen.BodyAndFuelScreen
import com.example.carsrecommendationapp.presentation.ui.screen.BrandSelectionScreen
import com.example.carsrecommendationapp.presentation.ui.screen.BudgetAndDetailsScreen
import com.example.carsrecommendationapp.presentation.ui.screen.DrivingHabitsScreen
import com.example.carsrecommendationapp.presentation.ui.screen.FuelSelectionScreen
import com.example.carsrecommendationapp.presentation.ui.screen.NameInputScreen
import com.example.carsrecommendationapp.presentation.ui.screen.ResultsScreen
import com.example.carsrecommendationapp.presentation.ui.screen.VehicleDetailsScreen
import com.example.carsrecommendationapp.presentation.ui.screen.WelcomeScreen
import com.example.carsrecommendationapp.presentation.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val onboardingViewModel: OnboardingViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = WelcomeRoute
            ) {
                composable<WelcomeRoute> {
                    WelcomeScreen(
                        onStartClick = {
                            navController.navigate(NameInputRoute)
                        }
                    )
                }

                composable<NameInputRoute> {
                    NameInputScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate(BrandsRoute)
                        }
                    )
                }

                composable<BrandsRoute> {
                    BrandSelectionScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate(BodyAndFuelRoute)
                        }
                    )
                }

                composable<BodyAndFuelRoute> {
                    BodyAndFuelScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate(FuelSelectionRoute)
                        }
                    )
                }

                composable<FuelSelectionRoute> {
                    FuelSelectionScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate(BudgetDetailsRoute)
                        }
                    )
                }

                composable<BudgetDetailsRoute> {
                    BudgetAndDetailsScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate(DrivingHabitsRoute)
                        }
                    )
                }

                composable<DrivingHabitsRoute> {
                    DrivingHabitsScreen(
                        onboardingViewModel = onboardingViewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onFindCarClick = {
                            navController.navigate(ResultsRoute)
                        }
                    )
                }

                composable<ResultsRoute> {
                    ResultsScreen(
                        onboardingViewModel = onboardingViewModel,
                        onCarClick = {
                            navController.navigate(VehicleDetailsRoute)
                        }
                    )
                }

                composable<VehicleDetailsRoute> {
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