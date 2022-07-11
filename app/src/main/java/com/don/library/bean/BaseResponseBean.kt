package com.don.library.bean

import java.io.Serializable

open class BaseResponseBean<T> : Serializable {

    var intervalUrl: String? = null

    open fun isSuccess(): Boolean {
        return false
    }

    open fun isLogout(): Boolean {
        return false
    }

    open fun getErrorMessage(): String? {
        return null
    }

    open fun getResponse(): T? {
        return null
    }

}