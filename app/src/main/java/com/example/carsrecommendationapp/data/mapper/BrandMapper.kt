package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.BrandDto
import com.example.carsrecommendationapp.domain.Brand

fun BrandDto.toDomain(): Brand {
    return Brand(
        naziv = naziv
    )
}