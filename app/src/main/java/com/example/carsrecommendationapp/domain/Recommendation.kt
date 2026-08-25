package com.example.carsrecommendationapp.domain

data class Recommendation(
    val id: Long,
    val brand: String,
    val model: String,
    val year: Int,
    val mileage: Int,
    val price: Int,
    val fuel: String,
    val bodyType: String,
    val transmission: String,
    val driveType: String,
    val score: Int
)