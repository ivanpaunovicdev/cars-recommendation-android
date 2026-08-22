package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.DriveTypeDto
import com.example.carsrecommendationapp.domain.DriveType

fun DriveTypeDto.toDomain(): DriveType {
    return DriveType(
        naziv = naziv
    )
}