package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.domain.Brand
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class BrandsViewModel : BaseListViewModel<Brand>() {

    val brands = items

    override suspend fun loadData(): List<Brand> {
        return carRepository.getBrands()
    }
}