package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.RecommendationDto
import com.example.carsrecommendationapp.domain.Recommendation

fun RecommendationDto.toDomain(): Recommendation {
    return Recommendation(
        id = requireNotNull(id) {
            "RecommendationDto.id must not be null."
        },
        brand = marka.orEmpty(),
        model = model.orEmpty(),
        year = godiste ?: 0,
        mileage = kilometraza ?: 0,
        price = cena ?: 0,
        fuel = gorivo.orEmpty(),
        bodyType = karoserija.orEmpty(),
        transmission = menjac.orEmpty(),
        driveType = pogon.orEmpty(),
        score = skor ?: 0
    )
}