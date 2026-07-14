package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.Fuel
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FuelsViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<Fuel>(carRepository) {

    val fuels = items

    override suspend fun loadData(): List<Fuel> {
        return carRepository.getFuels()
    }
}