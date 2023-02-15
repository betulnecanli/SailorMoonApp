package com.betulnecanli.sailormoonapp.data.remote.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


data class ApiResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("nextPage")
    val nextPage: Int,
    @SerializedName("prevPage")
    val prevPage: Int,
    @SerializedName("sailorMoon")
    val sailorMoon: List<SailorMoon>,
    @SerializedName("success")
    val success: Boolean
)