package com.adizdaroglu.cardealersapp.presentation.car_list

import com.adizdaroglu.cardealersapp.domain.model.Car

data class CarListState(
    val isLoading: Boolean = false,
    val carList: List<Car> = emptyList(),
    val error: String = ""
)