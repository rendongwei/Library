package com.don.library.weight.shape.interfaces

import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt

interface IColor {

    // 初始化
    fun initColor(view: View, attrs: AttributeSet?)

    // 设置字体透明度
    fun setTextColorAlpha(alpha: Float)

    // 设置背景透明度
    fun setBackgroundColorAlpha(alpha: Float)

    // 设置默认颜色
    fun setTextNormalColor(@ColorInt color: Int)

    // 设置按下颜色
    fun setTextPressedColor(@ColorInt color: Int)

    // 设置不可用颜色
    fun setTextNoEnableColor(@ColorInt color: Int)

    // 设置焦点颜色
    fun setTextFocusColor(@ColorInt color: Int)

    // 设置选中颜色
    fun setTextSelectedColor(@ColorInt color: Int)

    // 设置Color
    fun setColor()
}