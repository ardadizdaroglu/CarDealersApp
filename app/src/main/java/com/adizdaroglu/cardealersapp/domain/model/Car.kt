package com.adizdaroglu.cardealersapp.domain.model

import com.adizdaroglu.cardealersapp.data.remote.dto.Image
import com.adizdaroglu.cardealersapp.data.remote.dto.Seller

data class Car(
    val colour: String?,
    val description: String,
    val firstRegistration: String?,
    val fuel: String,
    val id: Int,
    val images: List<Image>?,
    val make: String,
    val mileage: Int,
    val model: String,
    val modelline: String?,
    val price: Int,
    val seller: Seller?
)