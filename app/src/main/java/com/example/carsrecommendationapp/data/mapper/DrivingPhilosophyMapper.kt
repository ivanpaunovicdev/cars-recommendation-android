package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.DrivingPhilosophyDto
import com.example.carsrecommendationapp.domain.DrivingPhilosophy

fun DrivingPhilosophyDto.toDomain(): DrivingPhilosophy {
    return DrivingPhilosophy(
        id = id,
        naziv = naziv,
        opis = opis
    )
}