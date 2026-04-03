package com.route.newsc43.data.api.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SourceDM(


    @field:SerializedName("country") val country: String? = null,

    @ColumnInfo @field:SerializedName("name") val name: String? = null,

    @field:SerializedName("description") val description: String? = null,

    @field:SerializedName("language") val language: String? = null,

    @PrimaryKey @field:SerializedName("id") val id: String = "0",

    @ColumnInfo @field:SerializedName("category") val category: String? = null,

    @field:SerializedName("url") val url: String? = null
)