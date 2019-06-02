package com.dlogan.android.offers.data.loader

import com.google.gson.annotations.SerializedName

data class OfferDto(

    val id: String,
    val url: String?,
    val name: String?,
    val description: String?,
    val terms: String?,
    @SerializedName("current_value")
    val currentValue: String?
)


