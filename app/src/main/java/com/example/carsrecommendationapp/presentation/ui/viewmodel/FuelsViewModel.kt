package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.domain.Fuel
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class FuelsViewModel : BaseListViewModel<Fuel>() {

    val fuels = items

    override suspend fun loadData(): List<Fuel> {
        return carRepository.getFuels()
    }
}