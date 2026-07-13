package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.domain.BodyType
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class BodyTypesViewModel : BaseListViewModel<BodyType>() {

    val bodyTypes = items

    override suspend fun loadData(): List<BodyType> {
        return carRepository.getBodyTypes()
    }
}