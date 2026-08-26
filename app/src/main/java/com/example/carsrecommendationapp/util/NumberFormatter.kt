package com.example.carsrecommendationapp.util

import java.text.NumberFormat
import java.util.Locale

private fun numberFormatter(): NumberFormat =
    NumberFormat.getNumberInstance(Locale.GERMANY)

fun formatPrice(price: Int?): String {
    return "€ ${numberFormatter().format(price ?: 0)}"
}

fun formatMileage(mileage: Int?): String {
    return numberFormatter().format(mileage ?: 0)
}

fun apiToUiTransmission(value: String): String {
    return when (value) {
        "Automatski / poluautomatski" -> "Automatik"
        "Manuelni" -> "Manuelni"
        else -> "Svejedno"
    }
}

fun uiToApiTransmission(value: String): String {
    return when (value) {
        "Automatik" -> "Automatski / poluautomatski"
        "Manuelni" -> "Manuelni"
        else -> ""
    }
}