package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.domain.Car
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class CarsViewModel : BaseListViewModel<Car>() {

    val cars = items

    override suspend fun loadData(): List<Car> {
        return carRepository.getCars()
    }
}