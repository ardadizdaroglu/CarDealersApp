package com.adizdaroglu.cardealersapp.data.remote

import com.adizdaroglu.cardealersapp.data.remote.dto.CarDto
import retrofit2.http.GET

interface CarDealersApi {

    @GET(".")
    suspend fun getCars(): List<CarDto>
}