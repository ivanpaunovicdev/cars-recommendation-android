package com.example.carsrecommendationapp

import androidx.navigation.toRoute
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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



            NavHost(
                navController = navController,
                startDestination = WelcomeRoute
            ) {

                composable<WelcomeRoute> {
                    WelcomeScreen(
                        onStartClick = {
                            navController.navigate(NameInputRoute) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable<NameInputRoute> {
                    NameInputScreen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate(BrandsRoute) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable<BrandsRoute> {
                    BrandSelectionScreen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate(BodyAndFuelRoute) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable<BodyAndFuelRoute> {
                    BodyAndFuelScreen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate(FuelSelectionRoute) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable<FuelSelectionRoute> {
                    FuelSelectionScreen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate(BudgetDetailsRoute) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable<BudgetDetailsRoute> {
                    BudgetAndDetailsScreen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onContinueClick = {
                            navController.navigate(DrivingHabitsRoute) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable<DrivingHabitsRoute> {
                    DrivingHabitsScreen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onFindCarClick = {
                            navController.navigate(ResultsRoute) {
                                launchSingleTop = true
                                popUpTo(DrivingHabitsRoute)
                            }
                        }
                    )
                }

                composable<ResultsRoute> {
                    ResultsScreen(
                        onCarClick = { carId ->
                            navController.navigate(
                                VehicleDetailsRoute(carId)
                            ) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable<VehicleDetailsRoute> { backStackEntry ->

                    val route = backStackEntry.toRoute<VehicleDetailsRoute>()

                    VehicleDetailsScreen(
                        carId = route.id,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}