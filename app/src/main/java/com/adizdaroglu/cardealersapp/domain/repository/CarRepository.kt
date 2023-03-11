package com.adizdaroglu.cardealersapp.domain.repository

import com.adizdaroglu.cardealersapp.data.remote.dto.CarDto

interface CarRepository {

    suspend fun getCars(): List<CarDto>
}