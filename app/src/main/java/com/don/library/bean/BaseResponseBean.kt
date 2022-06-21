package com.don.library.bean

import java.io.Serializable

open class BaseResponseBean<T> : Serializable {

    var code: Int = -1
    var data: T? = null
    var message: String? = null
    var loopUrl: String? = null

    open fun isSuccess(): Boolean {
        return false
    }

    open fun isLogout(): Boolean {
        return false
    }

}