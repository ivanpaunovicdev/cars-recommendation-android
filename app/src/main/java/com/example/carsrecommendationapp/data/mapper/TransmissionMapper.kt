package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.TransmissionDto
import com.example.carsrecommendationapp.domain.Transmission

fun TransmissionDto.toDomain(): Transmission {
    return Transmission(
        naziv = naziv
    )
}