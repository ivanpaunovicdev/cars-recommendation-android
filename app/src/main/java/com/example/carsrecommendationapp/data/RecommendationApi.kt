package com.example.carsrecommendationapp.data.network

import com.example.carsrecommendationapp.data.dto.RecommendationDto
import com.example.carsrecommendationapp.domain.BodyType
import com.example.carsrecommendationapp.domain.Brand
import com.example.carsrecommendationapp.domain.Car
import com.example.carsrecommendationapp.domain.DailyRoute
import com.example.carsrecommendationapp.domain.DriveType
import com.example.carsrecommendationapp.domain.DrivingPhilosophy
import com.example.carsrecommendationapp.domain.DrivingTerrain
import com.example.carsrecommendationapp.domain.Fuel
import com.example.carsrecommendationapp.domain.SafetyEquipment
import com.example.carsrecommendationapp.domain.Transmission
import retrofit2.http.GET
import retrofit2.http.Query


interface RecommendationApi {
    @GET("recommendations")
    suspend fun getRecommendations(
        @Query("budgetMin") budgetMin: Int = 0,
        @Query("budgetMax") budgetMax: Int = 100000,
        @Query("minYear") minYear: Int = 0,
        @Query("maxMileage") maxMileage: Int = Int.MAX_VALUE,

        @Query("brand") brands: List<String> = emptyList(),
        @Query("model") model: String = "",
        @Query("fuel") fuels: List<String> = emptyList(),
        @Query("bodyType") bodyTypes: List<String> = emptyList(),
        @Query("transmission") transmission: String = "",
        @Query("driveType") driveType: String = "",
        @Query("dailyRoute") dailyRoute: String = "",
        @Query("drivingTerrain") drivingTerrain: String = "",
        @Query("drivingPhilosophy") drivingPhilosophy: String = ""
    ): List<RecommendationDto>

    @GET("cars")
    suspend fun getCars(): List<Car>

    @GET("fuels")
    suspend fun getFuels(): List<Fuel>

    @GET("bodytypes")
    suspend fun getBodyTypes(): List<BodyType>

    @GET("brands")
    suspend fun getBrands(): List<Brand>

    @GET("transmissions")
    suspend fun getTransmissions(): List<Transmission>

    @GET("drivetypes")
    suspend fun getDriveTypes(): List<DriveType>

    @GET("safetyequipments")
    suspend fun getSafetyEquipments(): List<SafetyEquipment>

    @GET("models")
    suspend fun getModels(): List<String>

    @GET("dailyroutes")
    suspend fun getDailyRoutes(): List<DailyRoute>

    @GET("drivingterrains")
    suspend fun getDrivingTerrains(): List<DrivingTerrain>

    @GET("drivingphilosophies")
    suspend fun getDrivingPhilosophies(): List<DrivingPhilosophy>
}