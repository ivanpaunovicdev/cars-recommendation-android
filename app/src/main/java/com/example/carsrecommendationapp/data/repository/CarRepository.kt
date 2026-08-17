package com.example.carsrecommendationapp.data.repository

import com.example.carsrecommendationapp.domain.BodyType
import com.example.carsrecommendationapp.domain.Brand
import com.example.carsrecommendationapp.domain.Car
import com.example.carsrecommendationapp.domain.DailyRoute
import com.example.carsrecommendationapp.domain.DriveType
import com.example.carsrecommendationapp.domain.DrivingPhilosophy
import com.example.carsrecommendationapp.domain.DrivingTerrain
import com.example.carsrecommendationapp.domain.Fuel
import com.example.carsrecommendationapp.domain.Recommendation
import com.example.carsrecommendationapp.domain.SafetyEquipment
import com.example.carsrecommendationapp.domain.Transmission

interface CarRepository {

    suspend fun getCars(): List<Car>

    suspend fun getBrands(): List<Brand>

    suspend fun getFuels(): List<Fuel>

    suspend fun getBodyTypes(): List<BodyType>

    suspend fun getTransmissions(): List<Transmission>

    suspend fun getDriveTypes(): List<DriveType>

    suspend fun getSafetyEquipments(): List<SafetyEquipment>

    suspend fun getModels(): List<String>

    suspend fun getDailyRoutes(): List<DailyRoute>

    suspend fun getDrivingTerrains(): List<DrivingTerrain>

    suspend fun getDrivingPhilosophies(): List<DrivingPhilosophy>

    suspend fun getRecommendations(
        budgetMin: Int? = null,
        budgetMax: Int = 100000,
        minYear: Int = 0,
        maxMileage: Int? = null,
        brands: List<String> = emptyList(),
        model: String? = null,
        fuels: List<String> = emptyList(),
        bodyTypes: List<String> = emptyList(),
        transmission: String = "",
        driveType: String = "",
        dailyRoute: String = "",
        drivingTerrain: String = "",
        drivingPhilosophy: String = ""
    ): List<Recommendation>
}