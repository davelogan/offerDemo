package com.dlogan.android.offers.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offers")
data class Offer(

    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val url: String?,
    val name: String?,
    val description: String?,
    val terms: String?,
    @ColumnInfo(name = "current_value")
    val currentValue: String?,
    var isFavorite: Boolean = false
)

