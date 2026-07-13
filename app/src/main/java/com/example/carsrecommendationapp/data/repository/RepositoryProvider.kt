package com.example.carsrecommendationapp.data.repository

import com.example.carsrecommendationapp.data.network.RetrofitInstance

object RepositoryProvider {

    val carRepository: CarRepository by lazy {
        CarRepositoryImpl(RetrofitInstance.api)
    }
}