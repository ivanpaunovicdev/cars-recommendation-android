package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.network.RetrofitInstance
import com.example.carsrecommendationapp.domain.DailyRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DailyRoutesViewModel : ViewModel() {

    private val _dailyRoutes = MutableStateFlow<List<DailyRoute>>(emptyList())
    val dailyRoutes: StateFlow<List<DailyRoute>> = _dailyRoutes.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadDailyRoutes()
    }

    fun loadDailyRoutes() {
        viewModelScope.launch {
            try {
                _errorMessage.value = null
                _dailyRoutes.value = RetrofitInstance.api.getDailyRoutes()
            } catch (e: Exception) {
                _errorMessage.value = e.message
                e.printStackTrace()
            }
        }
    }
}