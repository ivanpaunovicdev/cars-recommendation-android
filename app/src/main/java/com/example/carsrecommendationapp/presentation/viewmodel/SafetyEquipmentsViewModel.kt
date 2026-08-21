package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.SafetyEquipment
import com.example.carsrecommendationapp.presentation.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SafetyEquipmentsViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<SafetyEquipment>(carRepository) {

    init {
        loadItems()
    }

    val safetyEquipments = items

    override suspend fun loadData(): List<SafetyEquipment> {
        return carRepository.getSafetyEquipments()
    }
}