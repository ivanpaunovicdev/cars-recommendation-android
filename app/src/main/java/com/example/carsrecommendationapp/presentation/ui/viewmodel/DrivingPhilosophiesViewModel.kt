package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.domain.DrivingPhilosophy
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class DrivingPhilosophiesViewModel : BaseListViewModel<DrivingPhilosophy>() {

    val drivingPhilosophies = items

    override suspend fun loadData(): List<DrivingPhilosophy> {
        return carRepository.getDrivingPhilosophies()
    }
}