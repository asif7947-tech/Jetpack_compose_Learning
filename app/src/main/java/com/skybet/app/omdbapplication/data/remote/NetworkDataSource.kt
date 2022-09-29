package com.skybet.app.omdbapplication.data.remote

import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun searchMoviesbyName(movieName:String) = apiService.featchMoviesbyName(movieName)

}