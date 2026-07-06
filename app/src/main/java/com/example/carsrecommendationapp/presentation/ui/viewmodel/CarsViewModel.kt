package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.network.RetrofitInstance
import com.example.carsrecommendationapp.domain.Car
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CarsViewModel : ViewModel() {

    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadCars()
    }

    fun loadCars() {
        viewModelScope.launch {
            try {
                _errorMessage.value = null
                _cars.value = RetrofitInstance.api.getCars()
            } catch (e: Exception) {
                _errorMessage.value = e.message
                e.printStackTrace()
            }
        }
    }
}