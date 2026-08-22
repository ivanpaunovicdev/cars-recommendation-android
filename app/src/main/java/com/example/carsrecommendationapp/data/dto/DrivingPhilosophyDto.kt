package com.example.carsrecommendationapp.data.dto

import com.google.gson.annotations.SerializedName

data class DrivingPhilosophyDto(

    @SerializedName("id")
    val id: String?,

    @SerializedName("naziv")
    val naziv: String?,

    @SerializedName("opis")
    val opis: String?
)