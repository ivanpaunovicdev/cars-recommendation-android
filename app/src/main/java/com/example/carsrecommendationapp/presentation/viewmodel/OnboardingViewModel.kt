package com.example.carsrecommendationapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.carsrecommendationapp.domain.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {

    private val _selectedBrands = MutableStateFlow<Set<String>>(emptySet())
    val selectedBrands: StateFlow<Set<String>> = _selectedBrands.asStateFlow()

    private val _selectedBodyTypes = MutableStateFlow<Set<String>>(emptySet())
    val selectedBodyTypes: StateFlow<Set<String>> = _selectedBodyTypes.asStateFlow()

    private val _selectedFuels = MutableStateFlow<Set<String>>(emptySet())
    val selectedFuels: StateFlow<Set<String>> = _selectedFuels.asStateFlow()

    private val _budgetMax = MutableStateFlow(100000)
    val budgetMax: StateFlow<Int> = _budgetMax.asStateFlow()

    private val _minYear = MutableStateFlow(2020)
    val minYear: StateFlow<Int> = _minYear.asStateFlow()

    private val _transmission = MutableStateFlow("")
    val transmission: StateFlow<String> = _transmission.asStateFlow()

    private val _selectedDailyRoute = MutableStateFlow("")
    val selectedDailyRoute: StateFlow<String> = _selectedDailyRoute.asStateFlow()

    private val _selectedDrivingTerrain = MutableStateFlow("")
    val selectedDrivingTerrain: StateFlow<String> = _selectedDrivingTerrain.asStateFlow()

    private val _selectedDrivingPhilosophy = MutableStateFlow("")
    val selectedDrivingPhilosophy: StateFlow<String> = _selectedDrivingPhilosophy.asStateFlow()

    private val _selectedRecommendation = MutableStateFlow<Recommendation?>(null)
    val selectedRecommendation: StateFlow<Recommendation?> = _selectedRecommendation.asStateFlow()

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    fun updateUserName(name: String) {
        _userName.value = name
    }

    fun updateSelectedRecommendation(recommendation: Recommendation) {
        _selectedRecommendation.value = recommendation
    }

    fun updateBrands(brands: Set<String>) {
        _selectedBrands.value = brands
    }

    fun updateBodyTypes(bodyTypes: Set<String>) {
        _selectedBodyTypes.value = bodyTypes
    }

    fun updateFuels(fuels: Set<String>) {
        _selectedFuels.value = fuels
    }

    fun updateBudgetMax(budget: Int) {
        _budgetMax.value = budget
    }

    fun updateMinYear(year: Int) {
        _minYear.value = year
    }

    fun updateTransmission(transmission: String) {
        _transmission.value = transmission
    }

    fun updateDailyRoute(route: String) {
        _selectedDailyRoute.value = route
    }

    fun updateDrivingTerrain(terrain: String) {
        _selectedDrivingTerrain.value = terrain
    }

    fun updateDrivingPhilosophy(philosophy: String) {
        _selectedDrivingPhilosophy.value = philosophy
    }
}