package com.skybet.app.omdbapplication.data.remote

import com.skybet.app.omdbapplication.data.response.MovieModel
import com.workerx.mycomposeapp.datalayer.remote.PostData

sealed class ApiResponseStates{
    class onSuccessResponse(val data :List<MovieModel>): ApiResponseStates()
    class onFailure(val message :String?): ApiResponseStates()
    class onNetworkFailure(val message :String?): ApiResponseStates()
    object Loading : ApiResponseStates()
    object Empty : ApiResponseStates()
}
