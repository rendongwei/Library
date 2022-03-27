package com.don.library.weight.shape.interfaces

import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import androidx.annotation.IntRange

interface ILayout {

    companion object {
        // 默认
        const val HIDE_RADIUS_SIDE_NONE = 0

        // 隐藏上圆角
        const val HIDE_RADIUS_SIDE_TOP = 1

        // 隐藏右圆角
        const val HIDE_RADIUS_SIDE_RIGHT = 2

        // 隐藏下圆角
        const val HIDE_RADIUS_SIDE_BOTTOM = 3

        // 隐藏左圆角
        const val HIDE_RADIUS_SIDE_LEFT = 4
    }

    @IntDef(
        value = [HIDE_RADIUS_SIDE_NONE, HIDE_RADIUS_SIDE_TOP, HIDE_RADIUS_SIDE_RIGHT, HIDE_RADIUS_SIDE_BOTTOM, HIDE_RADIUS_SIDE_LEFT]
    )
    @Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
    @Retention(AnnotationRetention.SOURCE)
    annotation class HideRadiusSide

    // 初始化
    fun initLayout(view: View, attrs: AttributeSet?)

    // 限制宽度
    fun setWidthLimit(widthLimit: Int): Boolean

    // 限制高度
    fun setHeightLimit(heightLimit: Int): Boolean

    // outline是否排除padding区域
    fun setOutlineExcludePadding(outlineExcludePadding: Boolean)

    // 设置阴影深度
    fun setShadowElevation(elevation: Int)

    // 获取阴影深度
    fun getShadowElevation(): Int

    // 设置阴影透明度
    fun setShadowAlpha(shadowAlpha: Float)

    // 获取阴影透明度
    fun getShadowAlpha(): Float

    // 设置阴影颜色
    fun setShadowColor(shadowColor: Int)

    // 获取阴影颜色
    fun getShadowColor(): Int

    // 设置圆角半径
    fun setRadius(radius: Int)

    // 设置圆角半径和隐藏圆角方向
    fun setRadius(radius: Int, @HideRadiusSide hideRadiusSide: Int)

    // 获取圆角半径
    fun getRadius(): Int

    // 设置outline
    fun setOutlineInset(left: Int, top: Int, right: Int, bottom: Int)

    // 是否在Build.VERSION_CODES.LOLLIPOP版本之前显示阴影
    fun setShowBorderOnlyBeforeL(showBorderOnlyBeforeL: Boolean)

    // 设置隐藏圆角的方向
    fun setHideRadiusSide(@HideRadiusSide hideRadiusSide: Int)

    // 获取隐藏圆角的方向
    fun getHideRadiusSide(): Int

    // 设置圆角和阴影
    fun setRadiusAndShadow(radius: Int, shadowElevation: Int, shadowAlpha: Float)

    // 设置圆角和阴影
    fun setRadiusAndShadow(
        radius: Int,
        @HideRadiusSide hideRadiusSide: Int,
        shadowElevation: Int,
        shadowAlpha: Float
    )

    // 设置圆角和阴影
    fun setRadiusAndShadow(
        radius: Int,
        @HideRadiusSide hideRadiusSide: Int,
        shadowElevation: Int,
        shadowColor: Int,
        shadowAlpha: Float
    )

    // 设置边框颜色
    fun setBorderColor(@ColorInt borderColor: Int)

    // 设置边框宽度
    fun setBorderWidth(borderWidth: Int)

    // 更新上分割线
    fun updateTopDivider(
        topInsetLeft: Int,
        topInsetRight: Int,
        topDividerHeight: Int,
        topDividerColor: Int
    )

    // 更新下分割线
    fun updateBottomDivider(
        bottomInsetLeft: Int,
        bottomInsetRight: Int,
        bottomDividerHeight: Int,
        bottomDividerColor: Int
    )

    // 更新左分割线
    fun updateLeftDivider(
        leftInsetTop: Int,
        leftInsetBottom: Int,
        leftDividerWidth: Int,
        leftDividerColor: Int
    )

    // 更新右分割线
    fun updateRightDivider(
        rightInsetTop: Int,
        rightInsetBottom: Int,
        rightDividerWidth: Int,
        rightDividerColor: Int
    )

    // 仅显示上分割线
    fun onlyShowTopDivider(
        topInsetLeft: Int,
        topInsetRight: Int,
        topDividerHeight: Int,
        topDividerColor: Int
    )

    // 仅显示下分割线
    fun onlyShowBottomDivider(
        bottomInsetLeft: Int,
        bottomInsetRight: Int,
        bottomDividerHeight: Int,
        bottomDividerColor: Int
    )

    // 仅显示左分割线
    fun onlyShowLeftDivider(
        leftInsetTop: Int,
        leftInsetBottom: Int,
        leftDividerWidth: Int,
        leftDividerColor: Int
    )

    // 仅显示右分割线
    fun onlyShowRightDivider(
        rightInsetTop: Int,
        rightInsetBottom: Int,
        rightDividerWidth: Int,
        rightDividerColor: Int
    )

    // 设置上分割线透明度[0,255]
    fun setTopDividerAlpha(@IntRange(from = 0, to = 255) dividerAlpha: Int)

    // 设置下分割线透明度[0,255]
    fun setBottomDividerAlpha(@IntRange(from = 0, to = 255) dividerAlpha: Int)

    // 设置左分割线透明度[0,255]
    fun setLeftDividerAlpha(@IntRange(from = 0, to = 255) dividerAlpha: Int)

    // 设置右分割线透明度[0,255]
    fun setRightDividerAlpha(@IntRange(from = 0, to = 255) dividerAlpha: Int)

    // 设置outer默认颜色(versionCode >= Build.VERSION_CODES.LOLLIPOP)
    fun setOuterNormalColor(color: Int)

    // 更新左分割线颜色
    fun updateLeftSeparatorColor(color: Int)

    // 更新右分割线颜色
    fun updateRightSeparatorColor(color: Int)

    // 更新上分割线颜色
    fun updateTopSeparatorColor(color: Int)

    // 更新下分割线颜色
    fun updateBottomSeparatorColor(color: Int)

    // 是否有上分割线
    fun hasTopSeparator(): Boolean

    // 是否有右分割线
    fun hasRightSeparator(): Boolean

    // 是否有左分割线
    fun hasLeftSeparator(): Boolean

    // 是否有下分割线
    fun hasBottomSeparator(): Boolean

    // 是否有边框
    fun hasBorder(): Boolean

}
