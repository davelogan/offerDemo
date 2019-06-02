package com.dlogan.android.offers.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "offers")
@Parcelize
data class Offer(

    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val url: String?,
    val name: String?,
    val description: String?,
    val terms: String?,
    @ColumnInfo(name = "current_value")
    val currentValue: String?,
    val isFavorite: Boolean = false
): Parcelable

