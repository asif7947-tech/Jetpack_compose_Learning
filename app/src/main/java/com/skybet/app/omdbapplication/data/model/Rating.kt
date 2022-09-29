package com.skybet.app.omdbapplication.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Rating(
    @SerializedName("Source")
    val source: String = "", // Internet Movie Database
    @SerializedName("Value")
    val value: String = "" // 7.7/10
)