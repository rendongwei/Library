package com.don.library.weight.shape.interfaces

import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View

interface IDrawable {

    // 初始化
    fun initDrawable(view: View, attrs: AttributeSet?)

    // 设置默认背景
    fun setBackgroundNormalDrawable(drawable: Drawable?)

    // 设置按下颜色
    fun setBackgroundPressedDrawable(drawable: Drawable?)

    // 设置不可用颜色
    fun setBackgroundNoEnableDrawable(drawable: Drawable?)

    // 设置焦点颜色
    fun setBackgroundFocusDrawable(drawable: Drawable?)

    // 设置选中颜色
    fun setBackgroundSelectedDrawable(drawable: Drawable?)

    // 设置Drawable
    fun setDrawable()
}