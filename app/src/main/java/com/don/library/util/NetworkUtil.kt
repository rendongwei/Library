package com.don.library.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun isNetworkConnect(context: Context): Boolean {
        var manager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (manager.activeNetworkInfo?.isAvailable == true ||
            manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.isAvailable == true
        ) {
            return true
        }
        return false
    }
}