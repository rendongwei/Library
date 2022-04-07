package com.don.library.util

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import com.don.library.extend.constrain

object ColorUtil {

    // 颜色是否为亮色
    fun isLightColor(@ColorRes color: Int): Boolean {
        val darkness =
            1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        if (darkness < 0.5) {
            return true
        }
        return false
    }

    // 获取两个颜色中间颜色(0.0f~1.0f)
    fun computeColor(@ColorInt fromColor: Int, @ColorInt toColor: Int, fraction: Float): Int {
        var fraction = fraction.constrain(0.0f, 1.0f)!!

        var fromColorAlpha = Color.alpha(fromColor)
        var toColorAlpha = Color.alpha(toColor)
        var resultAlpha = (toColorAlpha - fromColorAlpha).times(fraction).toInt() + fromColorAlpha


        var fromColorRed = Color.red(fromColor)
        var toColorRed = Color.red(toColor)
        var resultRed = (toColorRed - fromColorRed).times(fraction).toInt() + fromColorRed

        var fromColorGreen = Color.green(fromColor)
        var toColorGreen = Color.green(toColor)
        var resultGreen = (toColorGreen - fromColorGreen).times(fraction).toInt() + fromColorGreen

        var fromColorBlue = Color.blue(fromColor)
        var toColorBlue = Color.blue(toColor)
        var resultBlue = (toColorBlue - fromColorBlue).times(fraction).toInt() + fromColorBlue

        return Color.argb(resultAlpha, resultRed, resultGreen, resultBlue)
    }

    // 颜色转字符串
    fun colorToString(@ColorInt color: Int): String {
        return String.format("#%08X", color)
    }
}