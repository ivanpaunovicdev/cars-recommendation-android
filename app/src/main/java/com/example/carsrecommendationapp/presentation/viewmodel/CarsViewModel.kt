package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.Car
import com.example.carsrecommendationapp.presentation.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<Car>(carRepository) {

    val cars = items

    override suspend fun loadData(): List<Car> {
        return carRepository.getCars()
    }
}