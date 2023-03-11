package com.adizdaroglu.cardealersapp.data.di

import android.app.Application
import android.content.Context
import com.adizdaroglu.cardealersapp.common.Constants
import com.adizdaroglu.cardealersapp.data.remote.CarDealersApi
import com.adizdaroglu.cardealersapp.data.repository.CarRepositoryImpl
import com.adizdaroglu.cardealersapp.domain.repository.CarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCarDealersApi(): CarDealersApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarDealersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCarRepository(api: CarDealersApi): CarRepository {
        return CarRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}