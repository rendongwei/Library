package com.don.library.weight.shape.helper

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.View
import com.don.library.R
import com.don.library.extend.setBackgroundKeepPadding
import com.don.library.weight.shape.interfaces.IDrawable
import java.lang.ref.WeakReference

class DrawableHelper : IDrawable {

    private var mContext: Context? = null
    private var mOwner: WeakReference<View>? = null

    private var mNormalDrawable: Drawable? = null
    private var mPressedDrawable: Drawable? = null
    private var mNoEnableDrawable: Drawable? = null
    private var mFocusDrawable: Drawable? = null
    private var mSelectedDrawable: Drawable? = null

    override fun initDrawable(view: View, attrs: AttributeSet?) {
        mContext = view.context
        mOwner = WeakReference(view)
        if (attrs != null) {
            var typedArray = mContext!!.obtainStyledAttributes(
                attrs,
                R.styleable.Drawable,
                0,
                0
            )
            mNormalDrawable = typedArray.getDrawable(R.styleable.Drawable_backgroundNormalDrawable)
            mPressedDrawable =
                typedArray.getDrawable(R.styleable.Drawable_backgroundPressedDrawable)
            mNoEnableDrawable =
                typedArray.getDrawable(R.styleable.Drawable_backgroundNoEnableDrawable)
            mFocusDrawable = typedArray.getDrawable(R.styleable.Drawable_backgroundFocusDrawable)
            mSelectedDrawable =
                typedArray.getDrawable(R.styleable.Drawable_backgroundSelectedDrawable)
            typedArray.recycle()
        }
        setDrawable()
    }

    override fun setBackgroundNormalDrawable(drawable: Drawable?) {
        mNormalDrawable = drawable
    }

    override fun setBackgroundPressedDrawable(drawable: Drawable?) {
        mPressedDrawable = drawable
    }

    override fun setBackgroundNoEnableDrawable(drawable: Drawable?) {
        mNoEnableDrawable = drawable
    }

    override fun setBackgroundFocusDrawable(drawable: Drawable?) {
        mFocusDrawable = drawable
    }

    override fun setBackgroundSelectedDrawable(drawable: Drawable?) {
        mSelectedDrawable = drawable
    }

    override fun setDrawable() {
        if (!hasDrawable()) {
            return
        }
        mOwner?.get()?.apply {
            val drawable = StateListDrawable()
            mPressedDrawable?.apply {
                drawable.addState(intArrayOf(android.R.attr.state_pressed), this)
            }
            mNoEnableDrawable?.apply {
                drawable.addState(intArrayOf(-android.R.attr.state_enabled), this)
            }
            mFocusDrawable?.apply {
                drawable.addState(intArrayOf(android.R.attr.state_focused), this)
            }
            mSelectedDrawable?.apply {
                drawable.addState(intArrayOf(android.R.attr.state_selected), this)
            }
            if (mNormalDrawable == null) {
                drawable.addState(intArrayOf(), background)
            } else {
                drawable.addState(intArrayOf(), mNormalDrawable)
            }
            setBackgroundKeepPadding(drawable)
        }
    }

    private fun hasDrawable(): Boolean {
        return mPressedDrawable != null || mNoEnableDrawable != null || mFocusDrawable != null || mSelectedDrawable != null
    }
}