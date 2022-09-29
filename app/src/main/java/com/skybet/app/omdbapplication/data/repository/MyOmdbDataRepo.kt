package com.skybet.app.omdbapplication.data.repository

import com.skybet.app.omdbapplication.data.remote.NetworkDataSource
import com.skybet.app.omdbapplication.data.response.ApiDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MyOmdbDataRepo @Inject constructor(private val remoteDataSource: NetworkDataSource) {

    fun searchMoviesbyName(movieName:String): Flow<ApiDataResponse> = flow {
        emit(remoteDataSource.searchMoviesbyName(movieName=movieName))
    }.flowOn(Dispatchers.IO)
}