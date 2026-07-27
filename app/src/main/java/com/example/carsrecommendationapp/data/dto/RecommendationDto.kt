package com.example.carsrecommendationapp.data.dto

data class RecommendationDto(
    val marka: String?,
    val model: String?,
    val godiste: Int?,
    val kilometraza: Int?,
    val cena: Int?,
    val gorivo: String?,
    val karoserija: String?,
    val menjac: String?,
    val pogon: String?,
    val skor: Int?
)