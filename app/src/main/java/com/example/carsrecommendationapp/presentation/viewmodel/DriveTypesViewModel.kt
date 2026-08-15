package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.DriveType
import com.example.carsrecommendationapp.presentation.viewmodel.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DriveTypesViewModel @Inject constructor(
    carRepository: CarRepository
) : BaseListViewModel<DriveType>(carRepository) {

    val driveTypes = items

    override suspend fun loadData(): List<DriveType> {
        return carRepository.getDriveTypes()
    }
}