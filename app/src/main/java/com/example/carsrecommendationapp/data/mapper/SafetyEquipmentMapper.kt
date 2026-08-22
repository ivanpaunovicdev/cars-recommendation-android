package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.SafetyEquipmentDto
import com.example.carsrecommendationapp.domain.SafetyEquipment

fun SafetyEquipmentDto.toDomain(): SafetyEquipment {
    return SafetyEquipment(
        naziv = naziv
    )
}