package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.Transmission
import com.example.carsrecommendationapp.presentation.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransmissionsViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<Transmission>(carRepository) {

    init {
        loadItems()
    }

    val transmissions = items

    override suspend fun loadData(): List<Transmission> {
        return carRepository.getTransmissions()
    }
}