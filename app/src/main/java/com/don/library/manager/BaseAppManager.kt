package com.don.library.manager

import android.app.Application
import android.content.Context
import com.don.library.constants.Tag
import com.don.library.util.LogUtil

open class BaseAppManager {

    lateinit var mApplication: Application
    lateinit var mApplicationContext: Context

    open fun init(application: Application) {
        LogUtil.log(Tag.APP_MANAGER, "init: ${application.packageName}")
        mApplication = application
        mApplicationContext = application.applicationContext
    }
}