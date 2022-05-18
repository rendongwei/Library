package com.don.library.util

import android.content.Context
import android.widget.Toast

object ToastUtil {

    private var mToast: Toast? = null

    fun showToast(context: Context, text: CharSequence, duration: Int = Toast.LENGTH_LONG) {
        mToast?.cancel()
        mToast = Toast.makeText(context, text, duration)
        mToast?.show()
    }
}