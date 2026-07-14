package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.SafetyEquipment
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SafetyEquipmentsViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<SafetyEquipment>(carRepository) {

    val safetyEquipments = items

    override suspend fun loadData(): List<SafetyEquipment> {
        return carRepository.getSafetyEquipments()
    }
}