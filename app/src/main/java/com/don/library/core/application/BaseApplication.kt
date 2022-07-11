package com.don.library.core.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.don.library.constants.Tag
import com.don.library.manager.ActivityManager
import com.don.library.manager.ViewModelManager
import com.don.library.util.LogUtil

/**
 * Application基类
 */
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtil.log(Tag.APPLICATION, "onCreate")
        ViewModelManager.onCreate()
        registerActivityLifecycleCallbacks()
    }

    private fun registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                ActivityManager.addActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                ActivityManager.removeActivity(activity)
            }
        });
    }

    override fun onLowMemory() {
        super.onLowMemory()
        LogUtil.log(Tag.APPLICATION, "onLowMemory")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        LogUtil.log(Tag.APPLICATION, "onTrimMemory-$level")
    }

    override fun onTerminate() {
        super.onTerminate()
        LogUtil.log(Tag.APPLICATION, "onTerminate")
        ViewModelManager.onDestroy()
    }
}