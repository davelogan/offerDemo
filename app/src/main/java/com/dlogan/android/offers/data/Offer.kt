package com.dlogan.android.offers.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "offers")
data class Offer(

    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val url: String?,
    val name: String?,
    val description: String?,
    val terms: String?,
    @ColumnInfo(name = "current_value")
    @SerializedName("current_value") //TODO make a different object for parsing the json, this should not be here
    val currentValue: String?
)


