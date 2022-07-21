package com.don.library.weight.shape

import android.content.Context
import android.util.AttributeSet
import com.don.library.weight.shape.helper.DrawableHelper
import com.don.library.weight.shape.helper.ImageHelper
import com.don.library.weight.shape.helper.LayoutHelper
import com.don.library.weight.shape.helper.ShapeHelper
import com.don.library.weight.shape.interfaces.IDrawable
import com.don.library.weight.shape.interfaces.IImage
import com.don.library.weight.shape.interfaces.ILayout
import com.don.library.weight.shape.interfaces.IShape

open class ShapeImageView(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs), ILayout by LayoutHelper(),
    IShape by ShapeHelper(), IDrawable by DrawableHelper(), IImage by ImageHelper() {

    init {
        initLayout(this, attrs)
        initShape(this, attrs)
        initDrawable(this, attrs)
        initImage(this, attrs)
    }

}