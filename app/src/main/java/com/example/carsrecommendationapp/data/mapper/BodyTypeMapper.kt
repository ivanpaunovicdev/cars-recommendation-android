package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.BodyTypeDto
import com.example.carsrecommendationapp.domain.BodyType

fun BodyTypeDto.toDomain(): BodyType {
    return BodyType(
        naziv = naziv
    )
}