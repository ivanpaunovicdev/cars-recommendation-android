package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.domain.DrivingTerrain
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class DrivingTerrainsViewModel : BaseListViewModel<DrivingTerrain>() {

    val drivingTerrains = items

    override suspend fun loadData(): List<DrivingTerrain> {
        return carRepository.getDrivingTerrains()
    }
}