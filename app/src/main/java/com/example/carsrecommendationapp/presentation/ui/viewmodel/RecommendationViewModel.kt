package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.Recommendation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadRecommendations(
        budgetMin: Int = 0,
        budgetMax: Int = 100000,
        minYear: Int = 0,
        maxMileage: Int = Int.MAX_VALUE,
        brand: String = "",
        model: String = "",
        fuel: String = "",
        bodyType: String = "",
        transmission: String = "",
        driveType: String = ""
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null

                _recommendations.value = carRepository.getRecommendations(
                    budgetMin = budgetMin,
                    budgetMax = budgetMax,
                    minYear = minYear,
                    maxMileage = maxMileage,
                    brand = brand,
                    model = model,
                    fuel = fuel,
                    bodyType = bodyType,
                    transmission = transmission,
                    driveType = driveType
                )
            } catch (e: Exception) {
                _errorMessage.value =
                    e.message ?: "Došlo je do greške pri učitavanju preporuka."
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}