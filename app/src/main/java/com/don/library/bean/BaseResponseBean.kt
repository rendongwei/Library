package com.don.library.bean

import java.io.Serializable

data class BaseResponseBean<T>(
    var code: Int = -1,
    var data: T? = null,
    var message: String? = null,
) : Serializable {

    var loopUrl: String? = null

    open fun isSuccess(): Boolean {
        return false
    }

    open fun isLogout(): Boolean {
        return false
    }

}