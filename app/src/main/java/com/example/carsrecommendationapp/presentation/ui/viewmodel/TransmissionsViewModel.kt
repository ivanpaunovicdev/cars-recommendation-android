package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.domain.Transmission
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class TransmissionsViewModel : BaseListViewModel<Transmission>() {

    val transmissions = items

    override suspend fun loadData(): List<Transmission> {
        return carRepository.getTransmissions()
    }
}