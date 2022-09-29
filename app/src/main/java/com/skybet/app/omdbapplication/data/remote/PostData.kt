package com.workerx.mycomposeapp.datalayer.remote


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PostData(
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("body")
    var body: String
)