package com.adizdaroglu.cardealersapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Seller(
    @Json(name = "city") val city: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "type") val type: String
)