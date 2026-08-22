package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.DailyRouteDto
import com.example.carsrecommendationapp.domain.DailyRoute

fun DailyRouteDto.toDomain(): DailyRoute {
    return DailyRoute(
        id = id,
        naziv = naziv,
        opis = opis
    )
}