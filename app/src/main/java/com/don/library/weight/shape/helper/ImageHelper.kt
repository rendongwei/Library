package com.don.library.weight.shape.helper

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.widget.ImageView
import com.don.library.R
import com.don.library.weight.shape.interfaces.IImage
import java.lang.ref.WeakReference

class ImageHelper : IImage {

    private var mContext: Context? = null
    private var mOwner: WeakReference<ImageView>? = null

    private var mNormalDrawable: Drawable? = null
    private var mPressedDrawable: Drawable? = null
    private var mNoEnableDrawable: Drawable? = null
    private var mFocusDrawable: Drawable? = null
    private var mSelectedDrawable: Drawable? = null

    override fun initImage(view: ImageView, attrs: AttributeSet?) {
        mContext = view.context
        mOwner = WeakReference(view)
        if (attrs != null) {
            var typedArray = mContext!!.obtainStyledAttributes(
                attrs,
                R.styleable.Image,
                0,
                0
            )
            mNormalDrawable = typedArray.getDrawable(R.styleable.Image_normalSrc)
            mPressedDrawable =
                typedArray.getDrawable(R.styleable.Image_pressedSrc)
            mNoEnableDrawable =
                typedArray.getDrawable(R.styleable.Image_noEnableSrc)
            mFocusDrawable = typedArray.getDrawable(R.styleable.Image_focusSrc)
            mSelectedDrawable =
                typedArray.getDrawable(R.styleable.Image_selectedSrc)
            typedArray.recycle()
        }
        setImage()
    }

    override fun setNormalSrc(drawable: Drawable?) {
        mNormalDrawable = drawable
    }

    override fun setPressedSrc(drawable: Drawable?) {
        mPressedDrawable = drawable
    }

    override fun setNoEnableSrc(drawable: Drawable?) {
        mNoEnableDrawable = drawable
    }

    override fun setFocusSrc(drawable: Drawable?) {
        mFocusDrawable = drawable
    }

    override fun setSelectedSrc(drawable: Drawable?) {
        mSelectedDrawable = drawable
    }

    override fun setImage() {
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
                drawable.addState(intArrayOf(), this.drawable)
            } else {
                drawable.addState(intArrayOf(), mNormalDrawable)
            }
            setImageDrawable(drawable)
        }
    }

    private fun hasDrawable(): Boolean {
        return mPressedDrawable != null || mNoEnableDrawable != null || mFocusDrawable != null || mSelectedDrawable != null
    }
}