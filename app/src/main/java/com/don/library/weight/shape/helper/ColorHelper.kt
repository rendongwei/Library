package com.don.library.weight.shape.helper

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.graphics.ColorUtils
import com.don.library.R
import com.don.library.weight.shape.interfaces.IColor
import java.lang.ref.WeakReference

class ColorHelper : IColor {

    private var mContext: Context? = null
    private var mOwner: WeakReference<View>? = null

    private var mTextColorAlpha: Float = 1f
    private var mBackgroundColorAlpha: Float = 1f
    private var mNormalColor: Int = 0
    private var mPressedColor: Int = 0
    private var mNoEnableColor: Int = 0
    private var mFocusColor: Int = 0
    private var mSelectedColor: Int = 0

    override fun initColor(view: View, attrs: AttributeSet?) {
        mContext = view.context
        mOwner = WeakReference(view)
        if (attrs != null) {
            var typedArray = mContext!!.obtainStyledAttributes(
                attrs,
                R.styleable.Color,
                0,
                0
            )
            mTextColorAlpha = typedArray.getFloat(R.styleable.Color_textColorAlpha, 1f)
            mBackgroundColorAlpha = typedArray.getFloat(R.styleable.Color_backgroundColorAlpha, 1f)
            mNormalColor = typedArray.getColor(R.styleable.Color_textNormalColor, 0)
            mPressedColor = typedArray.getColor(R.styleable.Color_textPressedColor, 0)
            mNoEnableColor = typedArray.getColor(R.styleable.Color_textNoEnableColor, 0)
            mFocusColor = typedArray.getColor(R.styleable.Color_textFocusColor, 0)
            mSelectedColor = typedArray.getColor(R.styleable.Color_textSelectedColor, 0)
            typedArray.recycle()
        }
        setTextColorAlpha(mTextColorAlpha)
        setBackgroundColorAlpha(mBackgroundColorAlpha)
        setColor()
    }

    override fun setTextColorAlpha(alpha: Float) {
        mTextColorAlpha = alpha
        mOwner?.get()?.apply {
            if (this is TextView) {
                setTextColor(
                    ColorUtils.setAlphaComponent(
                        currentTextColor,
                        (255 * alpha).toInt().coerceIn(0, 255)
                    )
                )
            }
        }
    }

    override fun setBackgroundColorAlpha(alpha: Float) {
        mBackgroundColorAlpha = alpha
        mOwner?.get()?.apply {
            background?.alpha = (255 * alpha).toInt().coerceIn(0, 255)
        }
    }

    override fun setTextNormalColor(color: Int) {
        mNormalColor = color
    }

    override fun setTextPressedColor(color: Int) {
        mPressedColor = color
    }

    override fun setTextNoEnableColor(color: Int) {
        mNoEnableColor = color
    }

    override fun setTextFocusColor(color: Int) {
        mFocusColor = color
    }

    override fun setTextSelectedColor(color: Int) {
        mSelectedColor = color
    }

    override fun setColor() {
        if (!hasColor()) {
            return
        }
        mOwner?.get()?.apply {
            if (this is TextView) {
                val stateArray = mutableListOf<IntArray>()
                val colorArray = arrayListOf<Int>()
                if (mNormalColor == 0) {
                    mNormalColor = currentTextColor
                }

                if (mPressedColor != 0) {
                    stateArray.add(intArrayOf(android.R.attr.state_pressed))
                    colorArray.add(mPressedColor)
                }
                if (mNoEnableColor != 0) {
                    stateArray.add(intArrayOf(-android.R.attr.state_enabled))
                    colorArray.add(mNoEnableColor)
                }
                if (mFocusColor != 0) {
                    stateArray.add(intArrayOf(android.R.attr.state_focused))
                    colorArray.add(mFocusColor)
                }
                if (mSelectedColor != 0) {
                    stateArray.add(intArrayOf(android.R.attr.state_selected))
                    colorArray.add(mSelectedColor)
                }
                stateArray.add(intArrayOf())
                colorArray.add(mNormalColor)
                setTextColor(ColorStateList(stateArray.toTypedArray(), colorArray.toIntArray()))
            }
        }
    }

    private fun hasColor(): Boolean {
        return mPressedColor != 0 || mNoEnableColor != 0 || mFocusColor != 0 || mSelectedColor != 0
    }
}