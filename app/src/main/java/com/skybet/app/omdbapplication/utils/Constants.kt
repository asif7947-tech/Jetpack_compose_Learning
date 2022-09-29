package com.skybet.app.omdbapplication.utils

import com.skybet.app.omdbapplication.BuildConfig

object Constants {

    const val TIME_OUT = 30L
    // getBaseUrl function is for provide Server Base URL on RunTime
    fun getBaseUrl(): String {
        return  String.format(BuildConfig.BASE_SERVER_URL, "en")
    }


    // getApiKey function is for provide Api key on RunTime
    fun getApiKey(): String {
        return  String.format(BuildConfig.API_KEY, "en")
    }

}