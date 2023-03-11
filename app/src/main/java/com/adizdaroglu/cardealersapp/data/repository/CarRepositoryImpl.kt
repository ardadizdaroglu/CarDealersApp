package com.adizdaroglu.cardealersapp.data.repository

import com.adizdaroglu.cardealersapp.data.remote.CarDealersApi
import com.adizdaroglu.cardealersapp.data.remote.dto.CarDto
import com.adizdaroglu.cardealersapp.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val api: CarDealersApi
) : CarRepository {
    override suspend fun getCars(): List<CarDto> {
        return api.getCars()
    }
}