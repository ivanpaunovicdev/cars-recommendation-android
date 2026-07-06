package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.network.RetrofitInstance
import com.example.carsrecommendationapp.domain.DriveType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DriveTypesViewModel : ViewModel() {

    private val _driveTypes =
        MutableStateFlow<List<DriveType>>(emptyList())

    val driveTypes: StateFlow<List<DriveType>> =
        _driveTypes.asStateFlow()

    private val _errorMessage =
        MutableStateFlow<String?>(null)

    val errorMessage: StateFlow<String?> =
        _errorMessage.asStateFlow()

    init {
        loadDriveTypes()
    }

    fun loadDriveTypes() {
        viewModelScope.launch {
            try {
                _errorMessage.value = null

                _driveTypes.value =
                    RetrofitInstance.api.getDriveTypes()

            } catch (e: Exception) {
                _errorMessage.value = e.message
                e.printStackTrace()
            }
        }
    }
}