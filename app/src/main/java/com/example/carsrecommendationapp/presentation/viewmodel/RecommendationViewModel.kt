package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
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
    private val carRepository: CarRepository,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadRecommendations(
        budgetMin: Int? = null,
        budgetMax: Int = 100000,
        minYear: Int = 0,
        maxMileage: Int? = null,
        brands: List<String> = emptyList(),
        model: String? = null,
        fuels: List<String> = emptyList(),
        bodyTypes: List<String> = emptyList(),
        transmission: String = "",
        driveType: String = "",
        dailyRoute: String = "",
        drivingTerrain: String = "",
        drivingPhilosophy: String = ""
    ) {
        viewModelScope.launch(ioDispatcher) {
            try {
                _isLoading.value = true
                _errorMessage.value = null

                _recommendations.value = carRepository.getRecommendations(
                    budgetMin = budgetMin,
                    budgetMax = budgetMax,
                    minYear = minYear,
                    maxMileage = maxMileage,
                    brands = brands,
                    model = model,
                    fuels = fuels,
                    bodyTypes = bodyTypes,
                    transmission = transmission,
                    driveType = driveType,
                    dailyRoute = dailyRoute,
                    drivingTerrain = drivingTerrain,
                    drivingPhilosophy = drivingPhilosophy
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