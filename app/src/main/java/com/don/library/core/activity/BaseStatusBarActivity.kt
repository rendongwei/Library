package com.don.library.core.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.don.library.R
import com.don.library.core.activity.BaseActivity
import com.don.library.extend.color
import com.don.library.util.ColorUtil
import com.jaeger.library.StatusBarUtil


open abstract class BaseStatusBarActivity : BaseActivity() {

    // 获取状态栏颜色,默认白色
    open fun getStatusBarColor(): Int {
        return R.color.white
    }

    // 是否设置fitsSystemWindows属性
    open fun isFitsSystemWindows(): Boolean {
        return true
    }

    // 在此方法中重置状态栏
    open fun resetStatusBar() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFitsSystemWindows(isFitsSystemWindows())

        var color = color(getStatusBarColor())
        StatusBarUtil.setColorNoTranslucent(this, color)

        if (ColorUtil.isLightColor(color)) {
            StatusBarUtil.setLightMode(this)
        } else {
            StatusBarUtil.setDarkMode(this)
        }
        resetStatusBar()
    }

    fun setFitsSystemWindows(isFitsSystemWindows: Boolean) {
        val contentView: View =
            (window.decorView.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
        contentView.fitsSystemWindows = isFitsSystemWindows
    }


    // 设置标题栏透明
    fun setStatusBarTransparent() {
        StatusBarUtil.setTransparent(mActivity)
    }

    // 设置亮色模式
    fun setLightMode() {
        StatusBarUtil.setLightMode(mActivity)
    }

    // 设置黑暗模式
    fun setDarkMode() {
        StatusBarUtil.setDarkMode(mActivity)
    }
}