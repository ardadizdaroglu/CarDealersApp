package com.adizdaroglu.cardealersapp.data.remote.dto

import com.adizdaroglu.cardealersapp.domain.model.Car
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarDto(
    @Json(name = "colour") val colour: String?,
    @Json(name = "description") val description: String,
    @Json(name = "firstRegistration") val firstRegistration: String?,
    @Json(name = "fuel") val fuel: String,
    @Json(name = "id") val id: Int,
    @Json(name = "images") val images: List<Image>?,
    @Json(name = "make") val make: String,
    @Json(name = "mileage") val mileage: Int,
    @Json(name = "model") val model: String,
    @Json(name = "modelline") val modelline: String?,
    @Json(name = "price") val price: Int,
    @Json(name = "seller") val seller: Seller?
)

fun CarDto.toCar(): Car {
    return Car(
        colour = colour,
        description = description,
        firstRegistration = firstRegistration,
        fuel = fuel,
        id = id,
        images = images,
        make = make,
        mileage = mileage,
        model = model,
        modelline = modelline,
        price = price,
        seller = seller
    )
}