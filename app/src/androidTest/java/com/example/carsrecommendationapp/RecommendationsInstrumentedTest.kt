package com.example.carsrecommendationapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.carsrecommendationapp.presentation.ui.viewmodel.RecommendationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecommendationsInstrumentedTest {

    @Test
    fun testRecommendations_DieselHatchbackBudget() = runBlocking {
        val viewModel = RecommendationViewModel()

        viewModel.loadRecommendations(
            budgetMin = 5000,
            budgetMax = 15000,
            fuel = "Dizel",
            bodyType = "Hečbek"
        )

        delay(3000)

        val result = viewModel.recommendations.value
        val error = viewModel.errorMessage.value

        assertNull(error)
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.cena != null && it.cena <= 15000 })
        assertTrue(result.all { it.gorivo == "Dizel" })
        assertTrue(result.all { it.karoserija == "Hečbek" })
    }

    @Test
    fun testRecommendations_AudiMaxPrice() = runBlocking {
        val viewModel = RecommendationViewModel()

        viewModel.loadRecommendations(
            brand = "Audi",
            budgetMax = 15000
        )

        delay(3000)

        val result = viewModel.recommendations.value
        val error = viewModel.errorMessage.value

        assertNull(error)
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.marka == "Audi" })
        assertTrue(result.all { it.cena != null && it.cena <= 15000 })
    }

    @Test
    fun testRecommendations_MinYearMaxMileage() = runBlocking {
        val viewModel = RecommendationViewModel()

        viewModel.loadRecommendations(
            minYear = 2015,
            maxMileage = 200000
        )

        delay(3000)

        val result = viewModel.recommendations.value
        val error = viewModel.errorMessage.value

        assertNull(error)
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.godiste != null && it.godiste >= 2015 })
        assertTrue(result.all { it.kilometraza != null && it.kilometraza <= 200000 })
    }

    @Test
    fun testRecommendations_BMWDieselAutomatic() = runBlocking {
        val viewModel = RecommendationViewModel()

        viewModel.loadRecommendations(
            brand = "BMW",
            fuel = "Dizel",
            transmission = "Automatski / poluautomatski"
        )

        delay(3000)

        val result = viewModel.recommendations.value
        val error = viewModel.errorMessage.value

        assertNull(error)
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.marka == "BMW" })
        assertTrue(result.all { it.gorivo == "Dizel" })
        assertTrue(result.all { it.menjac == "Automatski / poluautomatski" })
    }

    @Test
    fun testRecommendations_VolkswagenGolf() = runBlocking {
        val viewModel = RecommendationViewModel()

        viewModel.loadRecommendations(
            brand = "Volkswagen",
            model = "Golf 7"
        )

        delay(3000)

        val result = viewModel.recommendations.value
        val error = viewModel.errorMessage.value

        assertNull(error)
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.marka == "Volkswagen" })
        assertTrue(result.all { it.model == "Golf 7" })
    }

    @Test
    fun testRecommendations_FrontDriveHatchback() = runBlocking {
        val viewModel = RecommendationViewModel()

        viewModel.loadRecommendations(
            driveType = "Prednji",
            bodyType = "Hečbek"
        )

        delay(3000)

        val result = viewModel.recommendations.value
        val error = viewModel.errorMessage.value

        assertNull(error)
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.pogon == "Prednji" })
        assertTrue(result.all { it.karoserija == "Hečbek" })
    }
}