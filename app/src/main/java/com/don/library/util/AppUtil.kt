package com.don.library.util

import android.content.Context

object AppUtil {

    fun getVersion(context: Context): Pair<String, Int> {
        var versionName = "1.0.0"
        var versionCode = 1
        try {
            val pm = context.packageManager.getPackageInfo(context.packageName, 0)
            versionName = pm.versionName
            versionCode = pm.versionCode
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Pair(versionName, versionCode)
    }
}