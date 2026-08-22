package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.DrivingTerrainDto
import com.example.carsrecommendationapp.domain.DrivingTerrain

fun DrivingTerrainDto.toDomain(): DrivingTerrain {
    return DrivingTerrain(
        id = id,
        naziv = naziv,
        opis = opis
    )
}