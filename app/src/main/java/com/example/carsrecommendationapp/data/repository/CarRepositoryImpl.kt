package com.example.carsrecommendationapp.data.repository

import com.example.carsrecommendationapp.data.mapper.toDomain
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
        brands: List<String>,
        model: String,
        fuels: List<String>,
        bodyTypes: List<String>,
        transmission: String,
        driveType: String,
        dailyRoute: String,
        drivingTerrain: String,
        drivingPhilosophy: String
    ): List<Recommendation> {
        return api.getRecommendations(
            budgetMin = budgetMin,
            budgetMax = budgetMax,
            minYear = minYear,
            maxMileage = maxMileage,
            brands = brands,
            model = model,
            fuels = fuels,
            bodyTypes = bodyTypes,
            transmission = transmission,
            driveType = driveType,
            dailyRoute = dailyRoute,
            drivingTerrain = drivingTerrain,
            drivingPhilosophy = drivingPhilosophy
        ).map { dto ->
            dto.toDomain()
        }
    }

}