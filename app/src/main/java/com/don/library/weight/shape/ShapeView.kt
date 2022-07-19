package com.don.library.weight.shape

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.don.library.weight.shape.helper.LayoutHelper
import com.don.library.weight.shape.helper.ShapeHelper
import com.don.library.weight.shape.interfaces.ILayout
import com.don.library.weight.shape.interfaces.IShape

open class ShapeView(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs), ILayout by LayoutHelper(), IShape by ShapeHelper() {

    init {
        initLayout(this, attrs)
        initLayout(this, attrs)
    }

}