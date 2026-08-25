package com.example.carsrecommendationapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleDetailsViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _car = MutableStateFlow<Car?>(null)
    val car: StateFlow<Car?> = _car.asStateFlow()

    fun loadCar(id: Long) {
        viewModelScope.launch {
            _car.value = carRepository.getCarById(id)
        }
    }
}