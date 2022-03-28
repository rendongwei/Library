package com.don.library.weight.shape.interfaces

import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt

interface IShape {

    // 初始化
    fun initShape(view: View, attrs: AttributeSet?)

    // 设置默认左上圆角
    fun setNormalLeftTopRadius(radius: Int)

    // 设置默认右上圆角
    fun setNormalRightTopRadius(radius: Int)

    // 设置默认左下圆角
    fun setNormalLeftBottomRadius(radius: Int)

    // 设置默认右下圆角
    fun setNormalRightBottomRadius(radius: Int)

    // 设置默认圆角
    fun setNormalRadius(radius: Int)

    // 设置默认左内距
    fun setNormalLeftPadding(padding: Int)

    // 设置默认上内距
    fun setNormalTopPadding(padding: Int)

    // 设置默认右内距
    fun setNormalRightPadding(padding: Int)

    // 设置默认下内距
    fun setNormalBottomPadding(padding: Int)

    // 设置默认内距
    fun setNormalPadding(padding: Int)

    // 设置默认背景色
    fun setNormalColor(@ColorInt color: Int)

    // 设置默认描线颜色
    fun setNormalStrokeColor(@ColorInt color: Int)

    // 设置默认描线宽度
    fun setNormalStrokeWidth(strokeWidth: Int)

    // 设置默认虚线宽度
    fun setNormalDashWidth(dashWidth: Int)

    // 设置默认虚线间距
    fun setNormalDashGap(dashGap: Int)

    // 设置按下左上圆角
    fun setPressedLeftTopRadius(radius: Int)

    // 设置按下右上圆角
    fun setPressedRightTopRadius(radius: Int)

    // 设置按下左下圆角
    fun setPressedLeftBottomRadius(radius: Int)

    // 设置按下右下圆角
    fun setPressedRightBottomRadius(radius: Int)

    // 设置按下圆角
    fun setPressedRadius(radius: Int)

    // 设置按下左内距
    fun setPressedLeftPadding(padding: Int)

    // 设置按下上内距
    fun setPressedTopPadding(padding: Int)

    // 设置按下右内距
    fun setPressedRightPadding(padding: Int)

    // 设置按下下内距
    fun setPressedBottomPadding(padding: Int)

    // 设置按下内距
    fun setPressedPadding(padding: Int)

    // 设置按下背景色
    fun setPressedColor(@ColorInt color: Int)

    // 设置按下描线颜色
    fun setPressedStrokeColor(@ColorInt color: Int)

    // 设置按下描线宽度
    fun setPressedStrokeWidth(strokeWidth: Int)

    // 设置按下虚线宽度
    fun setPressedDashWidth(dashWidth: Int)

    // 设置按下虚线间距
    fun setPressedDashGap(dashGap: Int)

    // 设置不可用左上圆角
    fun setNoEnableLeftTopRadius(radius: Int)

    // 设置不可用右上圆角
    fun setNoEnableRightTopRadius(radius: Int)

    // 设置不可用左下圆角
    fun setNoEnableLeftBottomRadius(radius: Int)

    // 设置不可用右下圆角
    fun setNoEnableRightBottomRadius(radius: Int)

    // 设置不可用圆角
    fun setNoEnableRadius(radius: Int)

    // 设置不可用左内距
    fun setNoEnableLeftPadding(padding: Int)

    // 设置不可用上内距
    fun setNoEnableTopPadding(padding: Int)

    // 设置不可用右内距
    fun setNoEnableRightPadding(padding: Int)

    // 设置不可用下内距
    fun setNoEnableBottomPadding(padding: Int)

    // 设置不可用内距
    fun setNoEnablePadding(padding: Int)

    // 设置不可用背景色
    fun setNoEnableColor(@ColorInt color: Int)

    // 设置不可用描线颜色
    fun setNoEnableStrokeColor(@ColorInt color: Int)

    // 设置不可用描线宽度
    fun setNoEnableStrokeWidth(strokeWidth: Int)

    // 设置不可用虚线宽度
    fun setNoEnableDashWidth(dashWidth: Int)

    // 设置不可用虚线间距
    fun setNoEnableDashGap(dashGap: Int)

    // 设置焦点左上圆角
    fun setFocusLeftTopRadius(radius: Int)

    // 设置焦点右上圆角
    fun setFocusRightTopRadius(radius: Int)

    // 设置焦点左下圆角
    fun setFocusLeftBottomRadius(radius: Int)

    // 设置焦点右下圆角
    fun setFocusRightBottomRadius(radius: Int)

    // 设置焦点圆角
    fun setFocusRadius(radius: Int)

    // 设置焦点左内距
    fun setFocusLeftPadding(padding: Int)

    // 设置焦点上内距
    fun setFocusTopPadding(padding: Int)

    // 设置焦点右内距
    fun setFocusRightPadding(padding: Int)

    // 设置焦点下内距
    fun setFocusBottomPadding(padding: Int)

    // 设置焦点内距
    fun setFocusPadding(padding: Int)

    // 设置焦点背景色
    fun setFocusColor(@ColorInt color: Int)

    // 设置焦点描线颜色
    fun setFocusStrokeColor(@ColorInt color: Int)

    // 设置焦点描线宽度
    fun setFocusStrokeWidth(strokeWidth: Int)

    // 设置焦点虚线宽度
    fun setFocusDashWidth(dashWidth: Int)

    // 设置焦点虚线间距
    fun setFocusDashGap(dashGap: Int)


    // 设置选中左上圆角
    fun setSelectedLeftTopRadius(radius: Int)

    // 设置选中右上圆角
    fun setSelectedRightTopRadius(radius: Int)

    // 设置选中左下圆角
    fun setSelectedLeftBottomRadius(radius: Int)

    // 设置选中右下圆角
    fun setSelectedRightBottomRadius(radius: Int)

    // 设置选中圆角
    fun setSelectedRadius(radius: Int)

    // 设置选中左内距
    fun setSelectedLeftPadding(padding: Int)

    // 设置选中上内距
    fun setSelectedTopPadding(padding: Int)

    // 设置选中右内距
    fun setSelectedRightPadding(padding: Int)

    // 设置选中下内距
    fun setSelectedBottomPadding(padding: Int)

    // 设置选中内距
    fun setSelectedPadding(padding: Int)

    // 设置选中背景色
    fun setSelectedColor(@ColorInt color: Int)

    // 设置选中描线颜色
    fun setSelectedStrokeColor(@ColorInt color: Int)

    // 设置选中描线宽度
    fun setSelectedStrokeWidth(strokeWidth: Int)

    // 设置选中虚线宽度
    fun setSelectedDashWidth(dashWidth: Int)

    // 设置选中虚线间距
    fun setSelectedDashGap(dashGap: Int)
    
    // 设置shape
    fun setShape()
}