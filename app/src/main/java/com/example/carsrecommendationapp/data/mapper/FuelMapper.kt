package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.FuelDto
import com.example.carsrecommendationapp.domain.Fuel

fun FuelDto.toDomain(): Fuel {
    return Fuel(
        naziv = naziv
    )
}