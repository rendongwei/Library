package com.don.library.weight.shape

import android.content.Context
import android.util.AttributeSet
import com.don.library.weight.shape.helper.ColorHelper
import com.don.library.weight.shape.helper.LayoutHelper
import com.don.library.weight.shape.helper.ShapeHelper
import com.don.library.weight.shape.interfaces.IColor
import com.don.library.weight.shape.interfaces.ILayout
import com.don.library.weight.shape.interfaces.IShape

open class ShapeTextView(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs), ILayout by LayoutHelper(),
    IShape by ShapeHelper(), IColor by ColorHelper() {

    init {
        initLayout(this, attrs)
        initShape(this, attrs)
        initColor(this, attrs)
        includeFontPadding = false
    }

}