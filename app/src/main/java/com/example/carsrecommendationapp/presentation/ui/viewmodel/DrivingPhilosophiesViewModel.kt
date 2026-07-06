package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.network.RetrofitInstance
import com.example.carsrecommendationapp.domain.DrivingPhilosophy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DrivingPhilosophiesViewModel : ViewModel() {

    private val _drivingPhilosophies = MutableStateFlow<List<DrivingPhilosophy>>(emptyList())
    val drivingPhilosophies: StateFlow<List<DrivingPhilosophy>> = _drivingPhilosophies.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadDrivingPhilosophies()
    }

    fun loadDrivingPhilosophies() {
        viewModelScope.launch {
            try {
                _errorMessage.value = null
                _drivingPhilosophies.value = RetrofitInstance.api.getDrivingPhilosophies()
            } catch (e: Exception) {
                _drivingPhilosophies.value = emptyList()
                _errorMessage.value = e.message
                e.printStackTrace()
            }
        }
    }
}