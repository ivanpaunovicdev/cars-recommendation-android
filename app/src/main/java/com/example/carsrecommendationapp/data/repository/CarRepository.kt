package com.example.carsrecommendationapp.data.repository

import com.example.carsrecommendationapp.domain.*

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
        budgetMin: Int = 0,
        budgetMax: Int = 100000,
        minYear: Int = 0,
        maxMileage: Int = Int.MAX_VALUE,
        brands: List<String> = emptyList(),
        model: String = "",
        fuels: List<String> = emptyList(),
        bodyTypes: List<String> = emptyList(),
        transmission: String = "",
        driveType: String = "",
        dailyRoute: String = "",
        drivingTerrain: String = "",
        drivingPhilosophy: String = ""
    ): List<Recommendation>
}