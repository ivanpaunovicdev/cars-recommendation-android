package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.DrivingPhilosophy
import com.example.carsrecommendationapp.presentation.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrivingPhilosophiesViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<DrivingPhilosophy>(carRepository) {

    init {
        loadItems()
    }

    val drivingPhilosophies = items

    override suspend fun loadData(): List<DrivingPhilosophy> {
        return carRepository.getDrivingPhilosophies()
    }
}