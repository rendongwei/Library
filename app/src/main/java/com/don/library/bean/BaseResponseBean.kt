package com.don.library.bean

import java.io.Serializable

open abstract class BaseResponseBean<T> : Serializable {

    val data: T? = null

    val message: String? = null

    var loopUrl: String? = null

    open fun isSuccess(): Boolean {
        return false
    }

    open fun isLogout(): Boolean {
        return false
    }

}