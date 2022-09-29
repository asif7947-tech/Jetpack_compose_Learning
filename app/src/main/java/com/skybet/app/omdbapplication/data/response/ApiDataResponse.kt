package com.skybet.app.omdbapplication.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ApiDataResponse(
    @SerializedName("Response")
    val response: String = "", // True
    @SerializedName("Search")
    val moviesList: List<MovieModel> = listOf(),
    @SerializedName("totalResults")
    val totalResults: String = "" // 31
)