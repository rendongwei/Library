package com.don.library.extend

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any?.toJson(): String? {
    this?.apply {
        return Gson().toJson(this)
    }
    return null
}

inline fun <reified T> String?.toBean(): T? {
    this?.apply {
        return try {
            Gson().fromJson(this, object : TypeToken<T>() {}.type)
        } catch (e: Exception) {
            null
        }
    }
    return null
}