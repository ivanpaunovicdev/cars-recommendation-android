package com.example.carsrecommendationapp.navigation

import kotlinx.serialization.Serializable

@Serializable
data object WelcomeRoute

@Serializable
data object NameInputRoute

@Serializable
data object BrandsRoute

@Serializable
data object BodyAndFuelRoute

@Serializable
data object FuelSelectionRoute

@Serializable
data object BudgetDetailsRoute

@Serializable
data object DrivingHabitsRoute

@Serializable
data object ResultsRoute

@Serializable
data class VehicleDetailsRoute(
    val id: Long
)