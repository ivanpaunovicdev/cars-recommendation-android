package com.example.carsrecommendationapp.presentation.viewmodel

data class RecommendationQuery(
    val budgetMin: Int? = null,
    val budgetMax: Int = 100000,
    val minYear: Int = 0,
    val maxMileage: Int? = null,
    val brands: List<String> = emptyList(),
    val model: String? = null,
    val fuels: List<String> = emptyList(),
    val bodyTypes: List<String> = emptyList(),
    val transmission: String = "",
    val driveType: String = "",
    val dailyRoute: String = "",
    val drivingTerrain: String = "",
    val drivingPhilosophy: String = ""
)