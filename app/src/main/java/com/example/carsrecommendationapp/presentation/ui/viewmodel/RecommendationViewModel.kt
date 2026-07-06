package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.network.RetrofitInstance
import com.example.carsrecommendationapp.domain.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecommendationViewModel : ViewModel() {

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadRecommendations()
    }

    fun loadRecommendations(
        budgetMin: Int = 0,
        budgetMax: Int = Int.MAX_VALUE,
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
                _errorMessage.value = null

                _recommendations.value = RetrofitInstance.api.getRecommendations(
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
                _errorMessage.value = e.message
                e.printStackTrace()
            }
        }
    }
}