package com.example.carsrecommendationapp.data.dto

import com.google.gson.annotations.SerializedName

data class RecommendationDto(

    @SerializedName("marka")
    val marka: String?,

    @SerializedName("model")
    val model: String?,

    @SerializedName("godiste")
    val godiste: Int?,

    @SerializedName("kilometraza")
    val kilometraza: Int?,

    @SerializedName("cena")
    val cena: Int?,

    @SerializedName("gorivo")
    val gorivo: String?,

    @SerializedName("karoserija")
    val karoserija: String?,

    @SerializedName("menjac")
    val menjac: String?,

    @SerializedName("pogon")
    val pogon: String?,

    @SerializedName("skor")
    val skor: Int?
)