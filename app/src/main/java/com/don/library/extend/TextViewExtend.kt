package com.xiaoerge.framework.extends

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

// 取文本
fun TextView.get(): String {
    return text.toString().trim()
}

// 判断是否为空
fun TextView.isNullOrEmpty(): Boolean {
    return get().isNullOrEmpty()
}

// 设置默认
fun TextView.setNormal(): TextView {
    setTypeface(
        Typeface.create(typeface, Typeface.NORMAL),
        Typeface.NORMAL
    )
    invalidate()
    return this
}

fun TextView.setBold(): TextView {
    setTypeface(
        Typeface.create(typeface, Typeface.BOLD),
        Typeface.BOLD
    )
    invalidate()
    return this
}

//// 设置加粗
//fun TextView.setBold(isBold: Boolean): TextView {
//    paint.isFakeBoldText = isBold
//    return this
//}
//
//// 设置加粗
//fun TextView.setBold(): TextView {
//    paint.flags = paint.flags or Paint.FAKE_BOLD_TEXT_FLAG
//    return this
//}

// 设置斜体
fun TextView.setItalic(): TextView {
    setTypeface(
        Typeface.create(typeface, Typeface.ITALIC),
        Typeface.ITALIC
    )
    invalidate()
    return this
}

// 设置粗斜体
fun TextView.setBoldItalic(): TextView {
    setTypeface(
        Typeface.create(typeface, Typeface.BOLD_ITALIC),
        Typeface.BOLD_ITALIC
    )
    invalidate()
    return this
}

// 设置删除线
fun TextView.setDeleteLine(): TextView {
    paint.flags = paint.flags or Paint.STRIKE_THRU_TEXT_FLAG
    return this
}

// 设置下划线
fun TextView.setUnderLine(): TextView {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    return this
}

// 设置字体
fun TextView.setFont(font: String): TextView {
    typeface = Typeface.createFromAsset(context.assets, font)
    return this
}

// 获取文本宽度
fun TextView.fontWidth(): Float {
    return fontWidth(get())
}

// 获取文本宽度
fun TextView.fontWidth(text: String): Float {
    return paint.measureText(text)
}

// 获取文字高度
fun TextView.fontHeight(): Float {
    var fm = paint.fontMetrics
    return fm.descent - fm.ascent
}

fun TextView.drawableEnd(resId: Int,padding: Int? = 0) {
    ContextCompat.getDrawable(context, resId)?.apply {
        setBounds(0, 0, minimumWidth, minimumHeight)
        if (padding != null) {
            compoundDrawablePadding = padding
        }
        setCompoundDrawables(null, null, this, null)
    }
}

// 显示键盘
fun EditText.showSoftInput() {
    requestFocus()
    var manager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.showSoftInput(this, 0)
}

// 隐藏键盘
fun EditText.hideSoftInput() {
    clearFocus()
    var manager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(windowToken, 0)
}
