package com.don.library.extend

import android.R
import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.don.library.data.MemoryCache
import com.don.library.util.ToastUtil

// 获取显示指标
fun AppCompatActivity.getDisplayMetrics() = baseContext.getDisplayMetrics()

// 获取屏幕宽度
fun AppCompatActivity.getScreenWidth() = baseContext.getScreenWidth()

// 获取屏幕高度
fun AppCompatActivity.getScreenHeight() = baseContext.getScreenHeight()

// 获取屏幕密度
fun AppCompatActivity.getScreenDensity() = baseContext.getScreenDensity()

// 获取屏幕密度Dpi
fun AppCompatActivity.getScreenDensityDpi() = baseContext.getScreenDensityDpi()

// 获取屏幕尺度密度
fun AppCompatActivity.getScreenScaleDensity() = baseContext.getScreenScaleDensity()

// 获取状态栏高度
fun AppCompatActivity.getStatusBarHeight(): Int {
    return baseContext.getStatusBarHeight()
}

// 获取底部栏高度
fun AppCompatActivity.getNavigationBarHeight(): Int {
    return baseContext.getNavigationBarHeight()
}

// 是否有底部栏
fun AppCompatActivity.checkDeviceHasNavigationBar(): Boolean {
    return baseContext.checkDeviceHasNavigationBar()
}

// dip转px
fun AppCompatActivity.dip2px(value: Float) = baseContext.dip2px(value)

// px转dip
fun AppCompatActivity.px2dip(value: Float) = baseContext.px2dip(value)

// sp转px
fun AppCompatActivity.sp2px(value: Float) = baseContext.sp2px(value)

// px转sp
fun AppCompatActivity.px2sp(value: Float) = baseContext.px2sp(value)

// 获取color
fun AppCompatActivity.color(@ColorRes id: Int) = baseContext.color(id)

// 获取color
fun AppCompatActivity.color(color: String) = baseContext.color(color)

// 获取drawable
fun AppCompatActivity.drawable(@DrawableRes id: Int) = baseContext.drawable(id)

// 获取string
fun AppCompatActivity.string(@StringRes id: Int) = baseContext.getString(id)

// 获取layout
fun AppCompatActivity.layout(@LayoutRes id: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        baseContext.layout(id)
    } else {
        (this as Context).layout(id)
    }

// 获取layout
fun AppCompatActivity.layout(@LayoutRes id: Int, parent: ViewGroup?, attachToRoot: Boolean) =
    baseContext.layout(id, parent, attachToRoot)

// 显示toast
fun AppCompatActivity.showToast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) =
    ToastUtil.showToast(baseContext, text, duration)


// 获取memoryCache
fun AppCompatActivity.memoryCache() = MemoryCache

// 监听键盘
fun AppCompatActivity.registerKeyboardListener(listener: ((height: Int, isOpen: Boolean) -> Unit)): ViewTreeObserver.OnGlobalLayoutListener {
    var rootView = (findViewById<View>(R.id.content) as ViewGroup).getChildAt(0)
    val statusHeight = getStatusBarHeight()
    val navigationHeight = getNavigationBarHeight()
    var isNavigationBarShow = false
    var listener = ViewTreeObserver.OnGlobalLayoutListener {
        val rect = Rect()
        rootView.getWindowVisibleDisplayFrame(rect)
        isNavigationBarShow = rootView.rootView.height - rect.bottom == navigationHeight
        val heightDiff: Int = rootView.rootView.height - rect.bottom
        val height = if (isNavigationBarShow) {
            heightDiff - navigationHeight
        } else {
            heightDiff
        }
        if (heightDiff > 100.toDp()) {
            listener.invoke(height, true)
        } else {
            listener.invoke(height, false)
        }
    }
    rootView.viewTreeObserver.addOnGlobalLayoutListener(listener)
    application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
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
            if (activity == this@registerKeyboardListener) {
                application.registerActivityLifecycleCallbacks(this)
                rootView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
            }
        }
    })
    return listener
}
