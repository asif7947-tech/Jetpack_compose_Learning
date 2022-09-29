package com.workerx.mycomposeapp.datalayer.remote

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class NetworkResponse<T>{
    @SerializedName("totalResults")
     var totalResults: String = ""
    @SerializedName("Response")
     var Response: String? = null
    @SerializedName("Search")
     val data: T?=null
 }
