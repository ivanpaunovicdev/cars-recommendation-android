package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.network.RetrofitInstance
import com.example.carsrecommendationapp.domain.Transmission
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransmissionsViewModel : ViewModel() {

    private val _transmissions =
        MutableStateFlow<List<Transmission>>(emptyList())

    val transmissions: StateFlow<List<Transmission>> =
        _transmissions.asStateFlow()

    private val _errorMessage =
        MutableStateFlow<String?>(null)

    val errorMessage: StateFlow<String?> =
        _errorMessage.asStateFlow()

    init {
        loadTransmissions()
    }

    fun loadTransmissions() {
        viewModelScope.launch {
            try {
                _errorMessage.value = null

                _transmissions.value =
                    RetrofitInstance.api.getTransmissions()

            } catch (e: Exception) {
                _errorMessage.value = e.message
                e.printStackTrace()
            }
        }
    }
}