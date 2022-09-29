package com.skybet.app.omdbapplication.data.remote

import com.skybet.app.omdbapplication.data.response.ApiDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun featchMoviesbyName(@Query("s") movie_name:String,
                                   @Query("apikey") apikey:String="ce23ee1b"): ApiDataResponse
}