package com.example.carsrecommendationapp.presentation.viewmodel

import com.example.carsrecommendationapp.data.repository.CarRepository
import com.example.carsrecommendationapp.domain.Recommendation
import com.example.carsrecommendationapp.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class RecommendationViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: CarRepository = mock()

    @Test
    fun `loadRecommendations updates recommendations`() = runTest {

        val expected = listOf(
            Recommendation(
                id = 1L,
                brand = "BMW",
                model = "320d",
                year = 2022,
                mileage = 50000,
                price = 25000,
                fuel = "Diesel",
                bodyType = "Sedan",
                transmission = "Automatic",
                driveType = "RWD",
                score = 95
            )
        )

        whenever(
            repository.getRecommendations(
                budgetMin = eq(null),
                budgetMax = eq(100000),
                minYear = eq(0),
                maxMileage = eq(null),
                brands = eq(emptyList()),
                model = eq(null),
                fuels = eq(emptyList()),
                bodyTypes = eq(emptyList()),
                transmission = eq(""),
                driveType = eq(""),
                dailyRoute = eq(""),
                drivingTerrain = eq(""),
                drivingPhilosophy = eq("")
            )
        ).thenReturn(expected)

        val viewModel = RecommendationViewModel(
            repository,
            mainDispatcherRule.dispatcher
        )

        viewModel.loadRecommendations()

        advanceUntilIdle()

        assertEquals(expected, viewModel.recommendations.value)
        assertFalse(viewModel.isLoading.value)
        assertNull(viewModel.errorMessage.value)
    }
}