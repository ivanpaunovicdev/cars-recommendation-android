package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.network.RetrofitInstance
import com.example.carsrecommendationapp.domain.SafetyEquipment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SafetyEquipmentsViewModel : ViewModel() {

    private val _safetyEquipments =
        MutableStateFlow<List<SafetyEquipment>>(emptyList())

    val safetyEquipments: StateFlow<List<SafetyEquipment>> =
        _safetyEquipments.asStateFlow()

    private val _errorMessage =
        MutableStateFlow<String?>(null)

    val errorMessage: StateFlow<String?> =
        _errorMessage.asStateFlow()

    init {
        loadSafetyEquipments()
    }

    fun loadSafetyEquipments() {
        viewModelScope.launch {
            try {
                _errorMessage.value = null

                _safetyEquipments.value =
                    RetrofitInstance.api.getSafetyEquipments()

            } catch (e: Exception) {
                _errorMessage.value = e.message
                e.printStackTrace()
            }
        }
    }
}