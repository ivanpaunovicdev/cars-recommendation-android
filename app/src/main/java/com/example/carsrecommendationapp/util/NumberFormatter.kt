package com.example.carsrecommendationapp.util

import java.text.NumberFormat
import java.util.Locale

private val SRBIAN_LOCALE = Locale("sr", "RS")

private fun numberFormatter(): NumberFormat =
    NumberFormat.getNumberInstance(SRBIAN_LOCALE)

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
        else -> value
    }
}

fun uiToApiTransmission(value: String): String {
    return when (value) {
        "Automatik" -> "Automatski / poluautomatski"
        "Manuelni" -> "Manuelni"
        else -> value
    }
}