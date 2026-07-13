package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.domain.SafetyEquipment
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class SafetyEquipmentsViewModel : BaseListViewModel<SafetyEquipment>() {

    val safetyEquipments = items

    override suspend fun loadData(): List<SafetyEquipment> {
        return carRepository.getSafetyEquipments()
    }
}