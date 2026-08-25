package com.example.carsrecommendationapp.data.network

import com.example.carsrecommendationapp.data.dto.RecommendationDto
import com.example.carsrecommendationapp.data.dto.BodyTypeDto
import com.example.carsrecommendationapp.data.dto.BrandDto
import com.example.carsrecommendationapp.data.dto.CarDto
import com.example.carsrecommendationapp.data.dto.DailyRouteDto
import com.example.carsrecommendationapp.data.dto.DrivingPhilosophyDto
import com.example.carsrecommendationapp.data.dto.DriveTypeDto
import com.example.carsrecommendationapp.data.dto.DrivingTerrainDto
import com.example.carsrecommendationapp.data.dto.FuelDto
import com.example.carsrecommendationapp.data.dto.SafetyEquipmentDto
import com.example.carsrecommendationapp.data.dto.TransmissionDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface RecommendationApi {
    @GET("recommendations")
    suspend fun getRecommendations(
        @Query("budgetMin") budgetMin: Int? = null,
        @Query("budgetMax") budgetMax: Int = 100000,
        @Query("minYear") minYear: Int = 0,
        @Query("maxMileage") maxMileage: Int? = null,

        @Query("brand") brands: List<String> = emptyList(),
        @Query("model") model: String? = null,
        @Query("fuel") fuels: List<String> = emptyList(),
        @Query("bodyType") bodyTypes: List<String> = emptyList(),
        @Query("transmission") transmission: String = "",
        @Query("driveType") driveType: String = "",
        @Query("dailyRoute") dailyRoute: String = "",
        @Query("drivingTerrain") drivingTerrain: String = "",
        @Query("drivingPhilosophy") drivingPhilosophy: String = ""
    ): List<RecommendationDto>

    @GET("cars")
    suspend fun getCars(): List<CarDto>

    @GET("cars/{id}")
    suspend fun getCarById(
        @Path("id") id: Long
    ): CarDto

    @GET("fuels")
    suspend fun getFuels(): List<FuelDto>

    @GET("bodytypes")
    suspend fun getBodyTypes(): List<BodyTypeDto>

    @GET("brands")
    suspend fun getBrands(): List<BrandDto>

    @GET("transmissions")
    suspend fun getTransmissions(): List<TransmissionDto>

    @GET("drivetypes")
    suspend fun getDriveTypes(): List<DriveTypeDto>

    @GET("safetyequipments")
    suspend fun getSafetyEquipments(): List<SafetyEquipmentDto>

    @GET("models")
    suspend fun getModels(): List<String>

    @GET("dailyroutes")
    suspend fun getDailyRoutes(): List<DailyRouteDto>

    @GET("drivingterrains")
    suspend fun getDrivingTerrains(): List<DrivingTerrainDto>

    @GET("drivingphilosophies")
    suspend fun getDrivingPhilosophies(): List<DrivingPhilosophyDto>
}