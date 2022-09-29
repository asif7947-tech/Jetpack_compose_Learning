package com.skybet.app.omdbapplication.utils

import android.content.Context

fun Context.getStringResourceByName(stringName: Int): String? {
    return resources.getString(stringName)
}