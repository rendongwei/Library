package com.don.library.weight.shape.interfaces

import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

interface IImage {

    // 初始化
    fun initImage(view: ImageView, attrs: AttributeSet?)

    // 设置默认背景
    fun setNormalSrc(drawable: Drawable?)

    // 设置按下颜色
    fun setPressedSrc(drawable: Drawable?)

    // 设置不可用颜色
    fun setNoEnableSrc(drawable: Drawable?)

    // 设置焦点颜色
    fun setFocusSrc(drawable: Drawable?)

    // 设置选中颜色
    fun setSelectedSrc(drawable: Drawable?)

    // 设置Image
    fun setImage()
}