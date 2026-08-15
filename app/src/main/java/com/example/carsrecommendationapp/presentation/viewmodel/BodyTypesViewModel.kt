package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.BodyType
import com.example.carsrecommendationapp.presentation.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BodyTypesViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<BodyType>(carRepository) {

    val bodyTypes = items

    override suspend fun loadData(): List<BodyType> {
        return carRepository.getBodyTypes()
    }
}