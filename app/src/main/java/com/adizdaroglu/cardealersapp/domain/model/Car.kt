package com.adizdaroglu.cardealersapp.domain.model

import android.os.Parcelable
import com.adizdaroglu.cardealersapp.data.remote.dto.Image
import com.adizdaroglu.cardealersapp.data.remote.dto.Seller
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Car(
    val colour: String?,
    val description: String?,
    val firstRegistration: String?,
    val fuel: String?,
    val id: Int?,
    val images: @RawValue List<Image>?,
    val make: String?,
    val mileage: Int?,
    val model: String?,
    val modelline: String?,
    val price: Int?,
    val seller: @RawValue Seller?
): Parcelable