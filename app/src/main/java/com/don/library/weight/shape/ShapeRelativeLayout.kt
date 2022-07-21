package com.don.library.weight.shape

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.lifecycle.*
import com.don.library.weight.shape.helper.ColorHelper
import com.don.library.weight.shape.helper.DrawableHelper
import com.don.library.weight.shape.helper.LayoutHelper
import com.don.library.weight.shape.helper.ShapeHelper
import com.don.library.weight.shape.interfaces.IColor
import com.don.library.weight.shape.interfaces.IDrawable
import com.don.library.weight.shape.interfaces.ILayout
import com.don.library.weight.shape.interfaces.IShape

open class ShapeRelativeLayout(
    context: Context,
    attrs: AttributeSet? = null
) : RelativeLayout(context, attrs), ILayout by LayoutHelper(), IShape by ShapeHelper(),
    IColor by ColorHelper(), IDrawable by DrawableHelper(),
    LifecycleOwner, ViewModelStoreOwner {

    private val mLifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }
    private val mViewModelStore by lazy {
        ViewModelStore()
    }

    init {
        initLayout(this, attrs)
        initShape(this, attrs)
        initColor(this, attrs)
        initDrawable(this, attrs)
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (mLifecycleRegistry.currentState == Lifecycle.State.CREATED) {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        } else {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

    override fun getViewModelStore(): ViewModelStore {
        return mViewModelStore
    }

}