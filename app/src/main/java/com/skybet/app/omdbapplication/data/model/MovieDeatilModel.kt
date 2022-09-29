package com.skybet.app.omdbapplication.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MovieDeatilModel(
    @SerializedName("Actors")
    val actors: String = "", // Chris Pratt, Will Ferrell, Elizabeth Banks
    @SerializedName("Awards")
    val awards: String = "", // Nominated for 1 Oscar. 72 wins & 67 nominations total
    @SerializedName("BoxOffice")
    val boxOffice: String = "", // $257,966,122
    @SerializedName("Country")
    val country: String = "", // United States, Denmark, Australia
    @SerializedName("DVD")
    val dVD: String = "", // 17 Jun 2014
    @SerializedName("Director")
    val director: String = "", // Phil Lord, Christopher Miller
    @SerializedName("Genre")
    val genre: String = "", // Animation, Action, Adventure
    @SerializedName("imdbID")
    val imdbID: String = "", // tt1490017
    @SerializedName("imdbRating")
    val imdbRating: String = "", // 7.7
    @SerializedName("imdbVotes")
    val imdbVotes: String = "", // 352,328
    @SerializedName("Language")
    val language: String = "", // English, Turkish
    @SerializedName("Metascore")
    val metascore: String = "", // 83
    @SerializedName("Plot")
    val plot: String = "", // An ordinary LEGO construction worker, thought to be the prophesied as "special", is recruited to join a quest to stop an evil tyrant from gluing the LEGO universe into eternal stasis.
    @SerializedName("Poster")
    val poster: String = "", // https://m.media-amazon.com/images/M/MV5BMTg4MDk1ODExN15BMl5BanBnXkFtZTgwNzIyNjg3MDE@._V1_SX300.jpg
    @SerializedName("Production")
    val production: String = "", // N/A
    @SerializedName("Rated")
    val rated: String = "", // PG
    @SerializedName("Ratings")
    val ratings: List<Rating> = listOf(),
    @SerializedName("Released")
    val released: String = "", // 07 Feb 2014
    @SerializedName("Response")
    val response: String = "", // True
    @SerializedName("Runtime")
    val runtime: String = "", // 100 min
    @SerializedName("Title")
    val title: String = "", // The Lego Movie
    @SerializedName("Type")
    val type: String = "", // movie
    @SerializedName("Website")
    val website: String = "", // N/A
    @SerializedName("Writer")
    val writer: String = "", // Phil Lord, Christopher Miller, Dan Hageman
    @SerializedName("Year")
    val year: String = "" // 2014
)