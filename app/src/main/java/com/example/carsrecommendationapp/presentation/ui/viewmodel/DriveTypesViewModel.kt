package com.example.carsrecommendationapp.presentation.ui.viewmodel

import com.example.carsrecommendationapp.domain.DriveType
import com.example.carsrecommendationapp.presentation.ui.viewmodel.base.BaseListViewModel

class DriveTypesViewModel : BaseListViewModel<DriveType>() {

    val driveTypes = items

    override suspend fun loadData(): List<DriveType> {
        return carRepository.getDriveTypes()
    }
}