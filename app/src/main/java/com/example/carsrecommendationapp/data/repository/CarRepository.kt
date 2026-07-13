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
        budgetMax: Int = Int.MAX_VALUE,
        minYear: Int = 0,
        maxMileage: Int = Int.MAX_VALUE,
        brand: String = "",
        model: String = "",
        fuel: String = "",
        bodyType: String = "",
        transmission: String = "",
        driveType: String = ""
    ): List<Recommendation>
}