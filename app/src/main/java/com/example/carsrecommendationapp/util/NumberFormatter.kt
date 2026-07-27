package com.example.carsrecommendationapp.util

import java.text.NumberFormat
import java.util.Locale

private val numberFormatter = NumberFormat.getNumberInstance(Locale.GERMANY)

fun formatPrice(price: Int?): String {
    return "€ ${numberFormatter.format(price ?: 0)}"
}

fun formatMileage(mileage: Int?): String {
    return numberFormatter.format(mileage ?: 0)
}