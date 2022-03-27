package com.don.library.extend

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.TouchDelegate
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat


/**
 * 设置背景并保持原来的padding
 */
fun View.setBackgroundKeepPadding(drawable: Drawable?) {
    var padding = intArrayOf(paddingLeft, paddingTop, paddingRight, paddingBottom)
    background = drawable
    setPadding(padding[0], padding[1], padding[2], padding[3])
}

/**
 * 设置背景并保持原来的padding
 */
fun View.setBackgroundKeepPadding(drawable: Int) {
    setBackgroundKeepPadding(ContextCompat.getDrawable(context, drawable))
}

/**
 * 设置背景并保持原来的padding
 */
fun View.setBackgroundColorKeepPadding(@ColorInt color: Int) {
    var padding = intArrayOf(paddingLeft, paddingTop, paddingRight, paddingBottom)
    setBackgroundColor(color)
    setPadding(padding[0], padding[1], padding[2], padding[3])
}

/**
 * 扩大点击范围(activity onWindowFocusChanged中调用)
 */
fun View.expendTouchArea(expendSize: Int) {
    val parent = (parent as View)
    parent.post {
        var rect = Rect()
        getHitRect(rect)
        rect.left -= expendSize
        rect.top -= expendSize
        rect.right += expendSize
        rect.bottom += expendSize
        parent.touchDelegate = TouchDelegate(rect, this)
    }
}