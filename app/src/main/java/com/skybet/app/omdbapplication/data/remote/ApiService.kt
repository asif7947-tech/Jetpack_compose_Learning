package com.workerx.mycomposeapp.datalayer.remote

import com.skybet.app.omdbapplication.data.response.ApiDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("?s={movie_name}&apikey=ce23ee1b")
    suspend fun searchMoviesbyName(@Path("movie_name") movie_name:String): ApiDataResponse

    @GET(".")
    suspend fun featchMoviesbyName(@Query("s") movie_name:String,
                                   @Query("apikey") apikey:String="ce23ee1b"): ApiDataResponse
}