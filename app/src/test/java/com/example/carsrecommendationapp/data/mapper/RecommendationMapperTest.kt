package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.RecommendationDto
import org.junit.Assert.assertEquals
import org.junit.Test

class RecommendationMapperTest {

    @Test
    fun `toDomain maps all fields correctly`() {

        val dto = RecommendationDto(
            id = 1L,
            marka = "BMW",
            model = "320d",
            godiste = 2022,
            kilometraza = 50000,
            cena = 25000,
            gorivo = "Dizel",
            karoserija = "Sedan",
            menjac = "Automatik",
            pogon = "Zadnji",
            skor = 95
        )

        val recommendation = dto.toDomain()

        assertEquals(1L, recommendation.id)
        assertEquals("BMW", recommendation.brand)
        assertEquals("320d", recommendation.model)
        assertEquals(2022, recommendation.year)
        assertEquals(50000, recommendation.mileage)
        assertEquals(25000, recommendation.price)
        assertEquals("Dizel", recommendation.fuel)
        assertEquals("Sedan", recommendation.bodyType)
        assertEquals("Automatik", recommendation.transmission)
        assertEquals("Zadnji", recommendation.driveType)
        assertEquals(95, recommendation.score)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `toDomain throws when id is null`() {

        val dto = RecommendationDto(
            id = null,
            marka = null,
            model = null,
            godiste = null,
            kilometraza = null,
            cena = null,
            gorivo = null,
            karoserija = null,
            menjac = null,
            pogon = null,
            skor = null
        )

        dto.toDomain()
    }
}