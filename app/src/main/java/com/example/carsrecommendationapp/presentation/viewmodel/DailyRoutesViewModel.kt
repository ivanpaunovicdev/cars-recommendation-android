package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.DailyRoute
import com.example.carsrecommendationapp.presentation.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyRoutesViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<DailyRoute>(carRepository) {

    init {
        loadItems()
    }

    val dailyRoutes = items

    override suspend fun loadData(): List<DailyRoute> {
        return carRepository.getDailyRoutes()
    }
}