package com.example.carsrecommendationapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedBrands =
        savedStateHandle.getMutableStateFlow(
            "selectedBrands",
            emptySet<String>()
        )
    val selectedBrands: StateFlow<Set<String>> = _selectedBrands.asStateFlow()

    private val _selectedBodyTypes =
        savedStateHandle.getMutableStateFlow(
            "selectedBodyTypes",
            emptySet<String>()
        )
    val selectedBodyTypes: StateFlow<Set<String>> = _selectedBodyTypes.asStateFlow()

    private val _selectedFuels =
        savedStateHandle.getMutableStateFlow(
            "selectedFuels",
            emptySet<String>()
        )
    val selectedFuels: StateFlow<Set<String>> = _selectedFuels.asStateFlow()

    private val _budgetMax =
        savedStateHandle.getMutableStateFlow(
            "budgetMax",
            100000
        )
    val budgetMax: StateFlow<Int> = _budgetMax.asStateFlow()

    private val _minYear =
        savedStateHandle.getMutableStateFlow(
            "minYear",
            2020
        )
    val minYear: StateFlow<Int> = _minYear.asStateFlow()

    private val _transmission =
        savedStateHandle.getMutableStateFlow(
            "transmission",
            ""
        )
    val transmission: StateFlow<String> = _transmission.asStateFlow()

    private val _driveType =
        savedStateHandle.getMutableStateFlow(
            "driveType",
            ""
        )
    val driveType: StateFlow<String> = _driveType.asStateFlow()

    private val _selectedDailyRoute =
        savedStateHandle.getMutableStateFlow(
            "selectedDailyRoute",
            ""
        )
    val selectedDailyRoute: StateFlow<String> = _selectedDailyRoute.asStateFlow()

    private val _selectedDrivingTerrain =
        savedStateHandle.getMutableStateFlow(
            "selectedDrivingTerrain",
            ""
        )
    val selectedDrivingTerrain: StateFlow<String> = _selectedDrivingTerrain.asStateFlow()

    private val _selectedDrivingPhilosophy =
        savedStateHandle.getMutableStateFlow(
            "selectedDrivingPhilosophy",
            ""
        )
    val selectedDrivingPhilosophy: StateFlow<String> = _selectedDrivingPhilosophy.asStateFlow()


    private val _userName =
        savedStateHandle.getMutableStateFlow(
            "userName",
            ""
        )

    val userName: StateFlow<String> = _userName.asStateFlow()

    fun updateUserName(name: String) {
        _userName.value = name
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

    fun updateDriveType(driveType: String) {
        _driveType.value = driveType
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