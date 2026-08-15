package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.Brand
import com.example.carsrecommendationapp.presentation.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<Brand>(carRepository) {

    val brands = items

    override suspend fun loadData(): List<Brand> {
        return carRepository.getBrands()
    }
}