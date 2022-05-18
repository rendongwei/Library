package com.don.library.extend

import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.don.library.data.MemoryCache
import com.don.library.util.ToastUtil

// 获取显示指标
fun Fragment.getDisplayMetrics() = requireContext().getDisplayMetrics()

// 获取屏幕宽度
fun Fragment.getScreenWidth() = requireContext().getScreenWidth()

// 获取屏幕高度
fun Fragment.getScreenHeight() = requireContext().getScreenHeight()

// 获取屏幕密度
fun Fragment.getScreenDensity() = requireContext().getScreenDensity()

// 获取屏幕密度Dpi
fun Fragment.getScreenDensityDpi() = requireContext().getScreenDensityDpi()

// 获取屏幕尺度密度
fun Fragment.getScreenScaleDensity() = requireContext().getScreenScaleDensity()

// 获取状态栏高度
fun Fragment.getStatusBarHeight(): Int {
    return requireContext().getStatusBarHeight()
}

// 获取底部栏高度
fun Fragment.getNavigationBarHeight(): Int {
    return requireContext().getNavigationBarHeight()
}

// 是否有底部栏
fun Fragment.checkDeviceHasNavigationBar(): Boolean {
    return requireContext().checkDeviceHasNavigationBar()
}

// dip转px
fun Fragment.dip2px(value: Float) = requireContext().dip2px(value)

// px转dip
fun Fragment.px2dip(value: Float) = requireContext().px2dip(value)

// sp转px
fun Fragment.sp2px(value: Float) = requireContext().sp2px(value)

// px转sp
fun Fragment.px2sp(value: Float) = requireContext().px2sp(value)

// 获取color
fun Fragment.color(@ColorRes id: Int) = requireContext().color(id)

// 获取color
fun Fragment.color(color: String) = requireContext().color(color)

// 获取drawable
fun Fragment.drawable(@DrawableRes id: Int) = requireContext().drawable(id)

// 获取string
fun Fragment.string(@StringRes id: Int) = getString(id)

// 获取layout
fun Fragment.layout(@LayoutRes id: Int) = requireContext().layout(id)

// 获取layout
fun Fragment.layout(@LayoutRes id: Int, parent: ViewGroup?, attachToRoot: Boolean) =
    requireContext().layout(id, parent, attachToRoot)

// 显示toast
fun Fragment.showToast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) =
    ToastUtil.showToast(requireContext(), text, duration)

// 获取memoryCache
fun Fragment.memoryCache() = MemoryCache
