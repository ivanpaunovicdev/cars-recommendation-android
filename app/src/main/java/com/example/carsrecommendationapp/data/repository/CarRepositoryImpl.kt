package com.example.carsrecommendationapp.data.repository

import com.example.carsrecommendationapp.data.mapper.toDomain
import com.example.carsrecommendationapp.data.network.RecommendationApi
import com.example.carsrecommendationapp.domain.Recommendation

class CarRepositoryImpl(
    private val api: RecommendationApi
) : CarRepository {

    override suspend fun getCars() =
        api.getCars().map { dto ->
            dto.toDomain()
        }

    override suspend fun getBrands() =
        api.getBrands().map { dto ->
            dto.toDomain()
        }

    override suspend fun getFuels() =
        api.getFuels().map { dto ->
            dto.toDomain()
        }

    override suspend fun getBodyTypes() =
        api.getBodyTypes().map { dto ->
            dto.toDomain()
        }

    override suspend fun getTransmissions() =
        api.getTransmissions().map { dto ->
            dto.toDomain()
        }

    override suspend fun getDriveTypes() =
        api.getDriveTypes().map { dto ->
            dto.toDomain()
        }

    override suspend fun getSafetyEquipments() =
        api.getSafetyEquipments().map { dto ->
            dto.toDomain()
        }

    override suspend fun getModels() =
        api.getModels()

    override suspend fun getDailyRoutes() =
        api.getDailyRoutes().map { dto ->
            dto.toDomain()
        }

    override suspend fun getDrivingTerrains() =
        api.getDrivingTerrains().map { dto ->
            dto.toDomain()
        }

    override suspend fun getDrivingPhilosophies() =
        api.getDrivingPhilosophies().map { dto ->
            dto.toDomain()
        }

    override suspend fun getRecommendations(
        budgetMin: Int?,
        budgetMax: Int,
        minYear: Int,
        maxMileage: Int?,
        brands: List<String>,
        model: String?,
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