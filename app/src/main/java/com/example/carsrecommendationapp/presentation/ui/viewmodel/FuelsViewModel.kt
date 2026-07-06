package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.network.RetrofitInstance
import com.example.carsrecommendationapp.domain.Fuel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FuelsViewModel : ViewModel() {

    private val _fuels = MutableStateFlow<List<Fuel>>(emptyList())
    val fuels: StateFlow<List<Fuel>> = _fuels.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadFuels()
    }

    fun loadFuels() {
        viewModelScope.launch {
            try {
                _errorMessage.value = null
                _fuels.value = RetrofitInstance.api.getFuels()
            } catch (e: Exception) {
                _errorMessage.value = e.message
                e.printStackTrace()
            }
        }
    }
}