package com.don.library.weight.shape

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.don.library.weight.shape.helper.LayoutHelper
import com.don.library.weight.shape.helper.ShapeHelper
import com.don.library.weight.shape.interfaces.ILayout
import com.don.library.weight.shape.interfaces.IShape

class ShapeLinearLayout(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs), ILayout by LayoutHelper(), IShape by ShapeHelper() {

    init {
        initLayout(this, attrs)
        initShape(this, attrs)
    }

}