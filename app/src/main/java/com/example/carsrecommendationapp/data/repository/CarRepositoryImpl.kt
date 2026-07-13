package com.example.carsrecommendationapp.data.repository

import com.example.carsrecommendationapp.data.network.RecommendationApi
import com.example.carsrecommendationapp.domain.*

class CarRepositoryImpl(
    private val api: RecommendationApi
) : CarRepository {

    override suspend fun getCars() =
        api.getCars()

    override suspend fun getBrands() =
        api.getBrands()

    override suspend fun getFuels() =
        api.getFuels()

    override suspend fun getBodyTypes() =
        api.getBodyTypes()

    override suspend fun getTransmissions() =
        api.getTransmissions()

    override suspend fun getDriveTypes() =
        api.getDriveTypes()

    override suspend fun getSafetyEquipments() =
        api.getSafetyEquipments()

    override suspend fun getModels() =
        api.getModels()

    override suspend fun getDailyRoutes() =
        api.getDailyRoutes()

    override suspend fun getDrivingTerrains() =
        api.getDrivingTerrains()

    override suspend fun getDrivingPhilosophies() =
        api.getDrivingPhilosophies()

    override suspend fun getRecommendations(
        budgetMin: Int,
        budgetMax: Int,
        minYear: Int,
        maxMileage: Int,
        brand: String,
        model: String,
        fuel: String,
        bodyType: String,
        transmission: String,
        driveType: String
    ) = api.getRecommendations(
        budgetMin = budgetMin,
        budgetMax = budgetMax,
        minYear = minYear,
        maxMileage = maxMileage,
        brand = brand,
        model = model,
        fuel = fuel,
        bodyType = bodyType,
        transmission = transmission,
        driveType = driveType
    )
}