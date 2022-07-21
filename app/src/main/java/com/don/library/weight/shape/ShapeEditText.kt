package com.don.library.weight.shape

import android.content.Context
import android.util.AttributeSet
import com.don.library.weight.shape.helper.ColorHelper
import com.don.library.weight.shape.helper.DrawableHelper
import com.don.library.weight.shape.helper.LayoutHelper
import com.don.library.weight.shape.helper.ShapeHelper
import com.don.library.weight.shape.interfaces.IColor
import com.don.library.weight.shape.interfaces.IDrawable
import com.don.library.weight.shape.interfaces.ILayout
import com.don.library.weight.shape.interfaces.IShape

open class ShapeEditText(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatEditText(context, attrs), ILayout by LayoutHelper(),
    IShape by ShapeHelper(), IColor by ColorHelper(), IDrawable by DrawableHelper() {

    init {
        initLayout(this, attrs)
        initShape(this, attrs)
        initColor(this, attrs)
        initDrawable(this, attrs)
        includeFontPadding = false
    }

}