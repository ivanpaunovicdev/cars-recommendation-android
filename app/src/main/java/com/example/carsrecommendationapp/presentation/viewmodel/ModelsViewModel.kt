package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.presentation.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ModelsViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<String>(carRepository) {

    val models = items

    override suspend fun loadData(): List<String> {
        return carRepository.getModels()
    }
}