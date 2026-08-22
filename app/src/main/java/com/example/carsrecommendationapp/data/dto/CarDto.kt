package com.example.carsrecommendationapp.data.dto

import com.google.gson.annotations.SerializedName

data class CarDto(

    @SerializedName("marka")
    val marka: String?,

    @SerializedName("model")
    val model: String?,

    @SerializedName("cena")
    val cena: Int?,

    @SerializedName("godiste")
    val godiste: Int?,

    @SerializedName("kilometraza")
    val kilometraza: Int?,

    @SerializedName("kubikaza")
    val kubikaza: Int?,

    @SerializedName("snagaKS")
    val snagaKS: Int?,

    @SerializedName("snagaKw")
    val snagaKw: Int?,

    @SerializedName("klima")
    val klima: String?,

    @SerializedName("stanje")
    val stanje: String?,

    @SerializedName("poreklo")
    val poreklo: String?,

    @SerializedName("brojSedista")
    val brojSedista: String?,

    @SerializedName("brojVrata")
    val brojVrata: String?,

    @SerializedName("ostecenje")
    val ostecenje: String?,

    @SerializedName("valuta")
    val valuta: String?
)