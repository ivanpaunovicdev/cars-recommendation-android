package com.example.carsrecommendationapp.data.repository

import kotlinx.coroutines.withContext
import com.example.carsrecommendationapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import com.example.carsrecommendationapp.Constants
import com.example.carsrecommendationapp.data.mapper.toDomain
import com.example.carsrecommendationapp.data.network.RecommendationApi
import com.example.carsrecommendationapp.domain.Recommendation

class CarRepositoryImpl(
    private val api: RecommendationApi,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : CarRepository {

    override suspend fun getCars() = withContext(ioDispatcher) {
        api.getCars().map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getCarById(
        id: Long
    ) = withContext(ioDispatcher) {
        api.getCarById(id).toDomain()
    }

    override suspend fun getBrands() = withContext(ioDispatcher) {
        api.getBrands().map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getFuels() = withContext(ioDispatcher) {
        api.getFuels().map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getBodyTypes() = withContext(ioDispatcher) {
        api.getBodyTypes().map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getTransmissions() = withContext(ioDispatcher) {
        api.getTransmissions().map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getDriveTypes() = withContext(ioDispatcher) {
        api.getDriveTypes().map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getSafetyEquipments() = withContext(ioDispatcher) {
        api.getSafetyEquipments().map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getModels() = withContext(ioDispatcher) {
        api.getModels()
    }

    override suspend fun getDailyRoutes() = withContext(ioDispatcher) {
        api.getDailyRoutes().map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getDrivingTerrains() = withContext(ioDispatcher) {
        api.getDrivingTerrains().map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getDrivingPhilosophies() = withContext(ioDispatcher) {
        api.getDrivingPhilosophies().map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getRecommendations(
        budgetMin: Int?,
        budgetMax: Int?,
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
    ): List<Recommendation> = withContext(ioDispatcher) {
        api.getRecommendations(
            budgetMin = budgetMin,
            budgetMax = budgetMax?.takeUnless { it >= Constants.NO_BUDGET_LIMIT },
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
        ).mapNotNull { dto ->
            dto.id?.let {
                dto.toDomain()
            }
        }
    }

}