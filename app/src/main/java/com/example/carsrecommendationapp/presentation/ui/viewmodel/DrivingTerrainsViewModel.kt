package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.network.RetrofitInstance
import com.example.carsrecommendationapp.domain.DrivingTerrain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DrivingTerrainsViewModel : ViewModel() {

    private val _drivingTerrains = MutableStateFlow<List<DrivingTerrain>>(emptyList())
    val drivingTerrains: StateFlow<List<DrivingTerrain>> = _drivingTerrains.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadDrivingTerrains()
    }

    fun loadDrivingTerrains() {
        viewModelScope.launch {
            try {
                _errorMessage.value = null
                _drivingTerrains.value = RetrofitInstance.api.getDrivingTerrains()
            } catch (e: Exception) {
                _errorMessage.value = e.message
                e.printStackTrace()
            }
        }
    }
}