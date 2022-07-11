package com.don.library.extend

import android.content.Context
import android.text.InputFilter
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.IntRange
import java.util.regex.Pattern

// 显示键盘
fun EditText.showSoftInput(): EditText {
    requestFocus()
    var manager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.showSoftInput(this, 0)
    return this
}

// 隐藏键盘
fun EditText.hideSoftInput(): EditText {
    clearFocus()
    var manager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(windowToken, 0)
    return this
}

fun EditText.setMaxLength(@IntRange(from = 0) length: Int): EditText {
    val inputFilters = mutableListOf<InputFilter>()
    inputFilters.addAll(filters)
    val lengthFilter = inputFilters.find {
        it is InputFilter.LengthFilter
    }
    lengthFilter?.apply {
        inputFilters.remove(lengthFilter)
    }
    inputFilters.add(InputFilter.LengthFilter(length))
    filters = inputFilters.toTypedArray()
    return this
}

fun EditText.setFirstNoBlank(): EditText {
    val inputFilters = mutableListOf<InputFilter>()
    inputFilters.addAll(filters)
    val inputFilter = InputFilter { source, _, _, _, dstart, _ ->
        if (source == " " && dstart == 0) {
            return@InputFilter ""
        }
        null
    }
    inputFilters.add(inputFilter)
    filters = inputFilters.toTypedArray()
    return this
}

fun EditText.setFirstNoZero(): EditText {
    val inputFilters = mutableListOf<InputFilter>()
    inputFilters.addAll(filters)
    val inputFilter = InputFilter { source, _, _, _, dstart, _ ->
        if (source == "0" && dstart == 0) {
            return@InputFilter ""
        }
        null
    }
    inputFilters.add(inputFilter)
    filters = inputFilters.toTypedArray()
    return this
}

fun EditText.setPointNum(length: Int, pointNum: Int) {
    val pattern = Pattern.compile("[0-9]{0,}+(\\.[0-9]{0,})?")
    val inputFilter = InputFilter { source, _, _, dest, _, _ ->
        val dValue = dest.toString()
        if (source == ".") {
            if (dest.isEmpty()) {
                return@InputFilter "0."
            } else if (dValue.contains(".")) {
                return@InputFilter ""
            }
        }
        if (dest.isEmpty() && source == "0") {
            return@InputFilter "0."
        }
        val matcher = pattern.matcher(source.toString())
        if (!matcher.matches()) {
            return@InputFilter ""
        }
        val splitArray = dValue.split("\\.".toRegex()).toTypedArray()
        if (splitArray.size > 1) {
            val numberValue = splitArray[0]
            val dotValue = splitArray[1]
            val curInputLength = numberValue.length + dotValue.length + 1

            if (curInputLength >= length) {
                return@InputFilter ""
            }

            if (inCursorBeforePoint() && curInputLength >= length) {
                return@InputFilter ""
            }

            if (!inCursorBeforePoint() && dotValue.length >= pointNum) {
                return@InputFilter ""
            }
        }
        null
    }
    setMaxLength(length)
    val inputFilters = mutableListOf<InputFilter>()
    inputFilters.addAll(filters)
    inputFilters.add(inputFilter)
    filters = inputFilters.toTypedArray()
}


fun EditText.inCursorBeforePoint(): Boolean {
    val text = text.toString().trim()
    val dotPosition = text.indexOf(".")
    if (dotPosition == -1) return true
    return dotPosition >= selectionStart
}