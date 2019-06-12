package com.dlogan.android.offers.entity

import androidx.room.ColumnInfo

data class OfferHeader (

    val id: String,

    val url: String?,

    val name: String?,

    @ColumnInfo(name = "current_value")
    val currentValue: String?,

    var isFavorite: Boolean = false
)