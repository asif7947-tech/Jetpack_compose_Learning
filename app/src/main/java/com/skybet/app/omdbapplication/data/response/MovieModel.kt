package com.skybet.app.omdbapplication.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MovieModel(
    @SerializedName("imdbID")
    val imdbID: String = "", // tt1375666
    @SerializedName("Poster")
    val poster: String = "", // https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg
    @SerializedName("Title")
    val title: String = "", // Inception
    @SerializedName("Type")
    val type: String = "", // movie
    @SerializedName("Year")
    val year: String = "" // 2010
)