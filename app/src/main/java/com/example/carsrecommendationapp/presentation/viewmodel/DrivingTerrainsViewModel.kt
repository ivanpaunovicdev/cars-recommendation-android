package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.DrivingTerrain
import com.example.carsrecommendationapp.presentation.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrivingTerrainsViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<DrivingTerrain>(carRepository) {

    val drivingTerrains = items

    override suspend fun loadData(): List<DrivingTerrain> {
        return carRepository.getDrivingTerrains()
    }
}