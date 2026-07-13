package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.domain.DailyRoute
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class DailyRoutesViewModel : BaseListViewModel<DailyRoute>() {

    val dailyRoutes = items

    override suspend fun loadData(): List<DailyRoute> {
        return carRepository.getDailyRoutes()
    }
}