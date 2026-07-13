package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class ModelsViewModel : BaseListViewModel<String>() {

    val models = items

    override suspend fun loadData(): List<String> {
        return carRepository.getModels()
    }
}