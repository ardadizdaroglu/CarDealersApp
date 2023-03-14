package com.adizdaroglu.cardealersapp.domain.usecase

import com.adizdaroglu.cardealersapp.common.Resource
import com.adizdaroglu.cardealersapp.data.remote.dto.toCar
import com.adizdaroglu.cardealersapp.domain.model.Car
import com.adizdaroglu.cardealersapp.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCarsUseCase @Inject constructor(
    private val repository: CarRepository
) {
    fun invokeCars(): Flow<Resource<List<Car>>> = flow {
        try {
            emit(Resource.Loading<List<Car>>())
            val cars = repository.getCars().map { it.toCar() }
            emit(Resource.Success<List<Car>>(cars))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Car>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException){
            emit(Resource.Error<List<Car>>("Couldn't reach to server. Check your internet connection."))
        }
    }
}