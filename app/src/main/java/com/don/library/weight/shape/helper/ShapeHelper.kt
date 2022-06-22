package com.don.library.weight.shape.helper

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.don.library.R
import com.don.library.extend.setBackgroundKeepPadding
import com.don.library.weight.shape.interfaces.IShape
import java.lang.ref.WeakReference

class ShapeHelper : IShape {

    private var mContext: Context? = null
    private var mOwner: WeakReference<View>? = null

    private var mNormalLeftTopRadius: Int = 0
    private var mNormalRightTopRadius: Int = 0
    private var mNormalLeftBottomRadius: Int = 0
    private var mNormalRightBottomRadius: Int = 0
    private var mNormalRadius: Int = 0
    private var mNormalLeftPadding: Int = 0
    private var mNormalTopPadding: Int = 0
    private var mNormalRightPadding: Int = 0
    private var mNormalBottomPadding: Int = 0
    private var mNormalPadding: Int = 0
    private var mNormalColor: Int = 0
    private var mNormalStrokeColor: Int = 0
    private var mNormalStrokeWidth: Int = 0
    private var mNormalDashWidth: Int = 0
    private var mNormalDashGap: Int = 0

    private var mPressedLeftTopRadius: Int = 0
    private var mPressedRightTopRadius: Int = 0
    private var mPressedLeftBottomRadius: Int = 0
    private var mPressedRightBottomRadius: Int = 0
    private var mPressedRadius: Int = 0
    private var mPressedLeftPadding: Int = 0
    private var mPressedTopPadding: Int = 0
    private var mPressedRightPadding: Int = 0
    private var mPressedBottomPadding: Int = 0
    private var mPressedPadding: Int = 0
    private var mPressedColor: Int = 0
    private var mPressedStrokeColor: Int = 0
    private var mPressedStrokeWidth: Int = 0
    private var mPressedDashWidth: Int = 0
    private var mPressedDashGap: Int = 0

    private var mNoEnableLeftTopRadius: Int = 0
    private var mNoEnableRightTopRadius: Int = 0
    private var mNoEnableLeftBottomRadius: Int = 0
    private var mNoEnableRightBottomRadius: Int = 0
    private var mNoEnableRadius: Int = 0
    private var mNoEnableLeftPadding: Int = 0
    private var mNoEnableTopPadding: Int = 0
    private var mNoEnableRightPadding: Int = 0
    private var mNoEnableBottomPadding: Int = 0
    private var mNoEnablePadding: Int = 0
    private var mNoEnableColor: Int = 0
    private var mNoEnableStrokeColor: Int = 0
    private var mNoEnableStrokeWidth: Int = 0
    private var mNoEnableDashWidth: Int = 0
    private var mNoEnableDashGap: Int = 0

    private var mFocusLeftTopRadius: Int = 0
    private var mFocusRightTopRadius: Int = 0
    private var mFocusLeftBottomRadius: Int = 0
    private var mFocusRightBottomRadius: Int = 0
    private var mFocusRadius: Int = 0
    private var mFocusLeftPadding: Int = 0
    private var mFocusTopPadding: Int = 0
    private var mFocusRightPadding: Int = 0
    private var mFocusBottomPadding: Int = 0
    private var mFocusPadding: Int = 0
    private var mFocusColor: Int = 0
    private var mFocusStrokeColor: Int = 0
    private var mFocusStrokeWidth: Int = 0
    private var mFocusDashWidth: Int = 0
    private var mFocusDashGap: Int = 0

    private var mSelectedLeftTopRadius: Int = 0
    private var mSelectedRightTopRadius: Int = 0
    private var mSelectedLeftBottomRadius: Int = 0
    private var mSelectedRightBottomRadius: Int = 0
    private var mSelectedRadius: Int = 0
    private var mSelectedLeftPadding: Int = 0
    private var mSelectedTopPadding: Int = 0
    private var mSelectedRightPadding: Int = 0
    private var mSelectedBottomPadding: Int = 0
    private var mSelectedPadding: Int = 0
    private var mSelectedColor: Int = 0
    private var mSelectedStrokeColor: Int = 0
    private var mSelectedStrokeWidth: Int = 0
    private var mSelectedDashWidth: Int = 0
    private var mSelectedDashGap: Int = 0

    override fun initShape(view: View, attrs: AttributeSet?) {
        mContext = view.context
        mOwner = WeakReference(view)
        if (attrs != null) {
            var typedArray = mContext!!.obtainStyledAttributes(
                attrs,
                R.styleable.Shape,
                0,
                0
            )
            mNormalLeftTopRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_normalLeftTopRadius, 0)
            mNormalRightTopRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_normalRightTopRadius, 0)
            mNormalLeftBottomRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_normalLeftBottomRadius, 0)
            mNormalRightBottomRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_normalRightBottomRadius, 0)
            mNormalRadius = typedArray.getDimensionPixelSize(R.styleable.Shape_normalRadius, 0)
            mNormalLeftPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_normalLeftPadding, 0)
            mNormalTopPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_normalTopPadding, 0)
            mNormalRightPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_normalRightPadding, 0)
            mNormalBottomPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_normalBottomPadding, 0)
            mNormalPadding = typedArray.getDimensionPixelSize(R.styleable.Shape_normalPadding, 0)
            mNormalColor = typedArray.getColor(R.styleable.Shape_normalColor, 0)
            mNormalStrokeColor = typedArray.getColor(R.styleable.Shape_normalStrokeColor, 0)
            mNormalStrokeWidth =
                typedArray.getDimensionPixelSize(R.styleable.Shape_normalStrokeWidth, 0)
            mNormalDashWidth =
                typedArray.getDimensionPixelSize(R.styleable.Shape_normalDashWidth, 0)
            mNormalDashGap = typedArray.getDimensionPixelSize(R.styleable.Shape_normalDashGap, 0)

            mPressedLeftTopRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_pressedLeftTopRadius, 0)
            mPressedRightTopRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_pressedRightTopRadius, 0)
            mPressedLeftBottomRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_pressedLeftBottomRadius, 0)
            mPressedRightBottomRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_pressedRightBottomRadius, 0)
            mPressedRadius = typedArray.getDimensionPixelSize(R.styleable.Shape_pressedRadius, 0)
            mPressedLeftPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_pressedLeftPadding, 0)
            mPressedTopPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_pressedTopPadding, 0)
            mPressedRightPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_pressedRightPadding, 0)
            mPressedBottomPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_pressedBottomPadding, 0)
            mPressedPadding = typedArray.getDimensionPixelSize(R.styleable.Shape_pressedPadding, 0)
            mPressedColor = typedArray.getColor(R.styleable.Shape_pressedColor, 0)
            mPressedStrokeColor = typedArray.getColor(R.styleable.Shape_pressedStrokeColor, 0)
            mPressedStrokeWidth =
                typedArray.getDimensionPixelSize(R.styleable.Shape_pressedStrokeWidth, 0)
            mPressedDashWidth =
                typedArray.getDimensionPixelSize(R.styleable.Shape_pressedDashWidth, 0)
            mPressedDashGap = typedArray.getDimensionPixelSize(R.styleable.Shape_pressedDashGap, 0)

            mNoEnableLeftTopRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableLeftTopRadius, 0)
            mNoEnableRightTopRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableRightTopRadius, 0)
            mNoEnableLeftBottomRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableLeftBottomRadius, 0)
            mNoEnableRightBottomRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableRightBottomRadius, 0)
            mNoEnableRadius = typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableRadius, 0)
            mNoEnableLeftPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableLeftPadding, 0)
            mNoEnableTopPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableTopPadding, 0)
            mNoEnableRightPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableRightPadding, 0)
            mNoEnableBottomPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableBottomPadding, 0)
            mNoEnablePadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnablePadding, 0)
            mNoEnableColor = typedArray.getColor(R.styleable.Shape_noEnableColor, 0)
            mNoEnableStrokeColor = typedArray.getColor(R.styleable.Shape_noEnableStrokeColor, 0)
            mNoEnableStrokeWidth =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableStrokeWidth, 0)
            mNoEnableDashWidth =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableDashWidth, 0)
            mNoEnableDashGap =
                typedArray.getDimensionPixelSize(R.styleable.Shape_noEnableDashGap, 0)

            mFocusLeftTopRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusLeftTopRadius, 0)
            mFocusRightTopRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusRightTopRadius, 0)
            mFocusLeftBottomRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusLeftBottomRadius, 0)
            mFocusRightBottomRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusRightBottomRadius, 0)
            mFocusRadius = typedArray.getDimensionPixelSize(R.styleable.Shape_focusRadius, 0)
            mFocusLeftPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusLeftPadding, 0)
            mFocusTopPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusTopPadding, 0)
            mFocusRightPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusRightPadding, 0)
            mFocusBottomPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusBottomPadding, 0)
            mFocusPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusPadding, 0)
            mFocusColor = typedArray.getColor(R.styleable.Shape_focusColor, 0)
            mFocusStrokeColor = typedArray.getColor(R.styleable.Shape_focusStrokeColor, 0)
            mFocusStrokeWidth =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusStrokeWidth, 0)
            mFocusDashWidth =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusDashWidth, 0)
            mFocusDashGap =
                typedArray.getDimensionPixelSize(R.styleable.Shape_focusDashGap, 0)

            mSelectedLeftTopRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedLeftTopRadius, 0)
            mSelectedRightTopRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedRightTopRadius, 0)
            mSelectedLeftBottomRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedLeftBottomRadius, 0)
            mSelectedRightBottomRadius =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedRightBottomRadius, 0)
            mSelectedRadius = typedArray.getDimensionPixelSize(R.styleable.Shape_selectedRadius, 0)
            mSelectedLeftPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedLeftPadding, 0)
            mSelectedTopPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedTopPadding, 0)
            mSelectedRightPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedRightPadding, 0)
            mSelectedBottomPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedBottomPadding, 0)
            mSelectedPadding =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedPadding, 0)
            mSelectedColor = typedArray.getColor(R.styleable.Shape_selectedColor, 0)
            mSelectedStrokeColor = typedArray.getColor(R.styleable.Shape_selectedStrokeColor, 0)
            mSelectedStrokeWidth =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedStrokeWidth, 0)
            mSelectedDashWidth =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedDashWidth, 0)
            mSelectedDashGap =
                typedArray.getDimensionPixelSize(R.styleable.Shape_selectedDashGap, 0)

            typedArray.recycle()
        }
        setShape()
    }

    override fun setNormalLeftTopRadius(radius: Int) {
        mNormalLeftTopRadius = radius
    }

    override fun setNormalRightTopRadius(radius: Int) {
        mNormalRightTopRadius = radius
    }

    override fun setNormalLeftBottomRadius(radius: Int) {
        mNormalLeftBottomRadius = radius
    }

    override fun setNormalRightBottomRadius(radius: Int) {
        mNormalRightBottomRadius = radius
    }

    override fun setNormalRadius(radius: Int) {
        mNormalRadius = radius
    }

    override fun setNormalLeftPadding(padding: Int) {
        mNormalLeftPadding = padding
    }

    override fun setNormalTopPadding(padding: Int) {
        mNormalTopPadding = padding
    }

    override fun setNormalRightPadding(padding: Int) {
        mNormalRightPadding = padding
    }

    override fun setNormalBottomPadding(padding: Int) {
        mNormalBottomPadding = padding
    }

    override fun setNormalPadding(padding: Int) {
        mNormalPadding = padding
    }

    override fun setNormalColor(@ColorInt color: Int) {
        mNormalColor = color
    }

    override fun setNormalStrokeColor(@ColorInt color: Int) {
        mNormalStrokeColor = color
    }

    override fun setNormalStrokeWidth(strokeWidth: Int) {
        mNormalStrokeWidth = strokeWidth
    }

    override fun setNormalDashWidth(dashWidth: Int) {
        mNormalDashWidth = dashWidth
    }

    override fun setNormalDashGap(dashGap: Int) {
        mNormalDashGap = dashGap
    }

    override fun setPressedLeftTopRadius(radius: Int) {
        mPressedLeftTopRadius = radius
    }

    override fun setPressedRightTopRadius(radius: Int) {
        mPressedRightTopRadius = radius
    }

    override fun setPressedLeftBottomRadius(radius: Int) {
        mPressedLeftBottomRadius = radius
    }

    override fun setPressedRightBottomRadius(radius: Int) {
        mPressedRightBottomRadius = radius
    }

    override fun setPressedRadius(radius: Int) {
        mPressedRadius = radius
    }

    override fun setPressedLeftPadding(padding: Int) {
        mPressedLeftPadding = padding
    }

    override fun setPressedTopPadding(padding: Int) {
        mPressedTopPadding = padding
    }

    override fun setPressedRightPadding(padding: Int) {
        mPressedRightPadding = padding
    }

    override fun setPressedBottomPadding(padding: Int) {
        mPressedBottomPadding = padding
    }

    override fun setPressedPadding(padding: Int) {
        mPressedPadding = padding
    }

    override fun setPressedColor(@ColorInt color: Int) {
        mPressedColor = color
    }

    override fun setPressedStrokeColor(@ColorInt color: Int) {
        mPressedStrokeColor = color
    }

    override fun setPressedStrokeWidth(strokeWidth: Int) {
        mPressedStrokeWidth = strokeWidth
    }

    override fun setPressedDashWidth(dashWidth: Int) {
        mPressedDashWidth = dashWidth
    }

    override fun setPressedDashGap(dashGap: Int) {
        mPressedDashGap = dashGap
    }

    override fun setNoEnableLeftTopRadius(radius: Int) {
        mNoEnableLeftTopRadius = radius
    }

    override fun setNoEnableRightTopRadius(radius: Int) {
        mNoEnableRightTopRadius = radius
    }

    override fun setNoEnableLeftBottomRadius(radius: Int) {
        mNoEnableLeftBottomRadius = radius
    }

    override fun setNoEnableRightBottomRadius(radius: Int) {
        mNoEnableRightBottomRadius = radius
    }

    override fun setNoEnableRadius(radius: Int) {
        mNoEnableRadius = radius
    }

    override fun setNoEnableLeftPadding(padding: Int) {
        mNoEnableLeftPadding = padding
    }

    override fun setNoEnableTopPadding(padding: Int) {
        mNoEnableTopPadding = padding
    }

    override fun setNoEnableRightPadding(padding: Int) {
        mNoEnableRightPadding = padding
    }

    override fun setNoEnableBottomPadding(padding: Int) {
        mNoEnableBottomPadding = padding
    }

    override fun setNoEnablePadding(padding: Int) {
        mNoEnablePadding = padding
    }

    override fun setNoEnableColor(@ColorInt color: Int) {
        mNoEnableColor = color
    }

    override fun setNoEnableStrokeColor(@ColorInt color: Int) {
        mNoEnableStrokeColor = color
    }

    override fun setNoEnableStrokeWidth(strokeWidth: Int) {
        mNoEnableStrokeWidth = strokeWidth
    }

    override fun setNoEnableDashWidth(dashWidth: Int) {
        mNoEnableDashWidth = dashWidth
    }

    override fun setNoEnableDashGap(dashGap: Int) {
        mNoEnableDashGap = dashGap
    }


    override fun setFocusLeftTopRadius(radius: Int) {
        mFocusLeftTopRadius = radius
    }

    override fun setFocusRightTopRadius(radius: Int) {
        mFocusRightTopRadius = radius
    }

    override fun setFocusLeftBottomRadius(radius: Int) {
        mFocusLeftBottomRadius = radius
    }

    override fun setFocusRightBottomRadius(radius: Int) {
        mFocusRightBottomRadius = radius
    }

    override fun setFocusRadius(radius: Int) {
        mFocusRadius = radius
    }

    override fun setFocusLeftPadding(padding: Int) {
        mFocusLeftPadding = padding
    }

    override fun setFocusTopPadding(padding: Int) {
        mFocusTopPadding = padding
    }

    override fun setFocusRightPadding(padding: Int) {
        mFocusRightPadding = padding
    }

    override fun setFocusBottomPadding(padding: Int) {
        mFocusBottomPadding = padding
    }

    override fun setFocusPadding(padding: Int) {
        mFocusPadding = padding
    }

    override fun setFocusColor(@ColorInt color: Int) {
        mFocusColor = color
    }

    override fun setFocusStrokeColor(@ColorInt color: Int) {
        mFocusStrokeColor = color
    }

    override fun setFocusStrokeWidth(strokeWidth: Int) {
        mFocusStrokeWidth = strokeWidth
    }

    override fun setFocusDashWidth(dashWidth: Int) {
        mFocusDashWidth = dashWidth
    }

    override fun setFocusDashGap(dashGap: Int) {
        mFocusDashGap = dashGap
    }

    override fun setSelectedLeftTopRadius(radius: Int) {
        mSelectedLeftTopRadius = radius
    }

    override fun setSelectedRightTopRadius(radius: Int) {
        mSelectedRightTopRadius = radius
    }

    override fun setSelectedLeftBottomRadius(radius: Int) {
        mSelectedLeftBottomRadius = radius
    }

    override fun setSelectedRightBottomRadius(radius: Int) {
        mSelectedRightBottomRadius = radius
    }

    override fun setSelectedRadius(radius: Int) {
        mSelectedRadius = radius
    }

    override fun setSelectedLeftPadding(padding: Int) {
        mSelectedLeftPadding = padding
    }

    override fun setSelectedTopPadding(padding: Int) {
        mSelectedTopPadding = padding
    }

    override fun setSelectedRightPadding(padding: Int) {
        mSelectedRightPadding = padding
    }

    override fun setSelectedBottomPadding(padding: Int) {
        mSelectedBottomPadding = padding
    }

    override fun setSelectedPadding(padding: Int) {
        mSelectedPadding = padding
    }

    override fun setSelectedColor(@ColorInt color: Int) {
        mSelectedColor = color
    }

    override fun setSelectedStrokeColor(@ColorInt color: Int) {
        mSelectedStrokeColor = color
    }

    override fun setSelectedStrokeWidth(strokeWidth: Int) {
        mSelectedStrokeWidth = strokeWidth
    }

    override fun setSelectedDashWidth(dashWidth: Int) {
        mSelectedDashWidth = dashWidth
    }

    override fun setSelectedDashGap(dashGap: Int) {
        mSelectedDashGap = dashGap
    }
    
    override fun setShape() {
        mOwner?.get()?.apply {
            val drawable = StateListDrawable()
            val pressedDrawable = getShape(
                mPressedLeftTopRadius,
                mPressedRightTopRadius,
                mPressedLeftBottomRadius,
                mPressedRightBottomRadius,
                mPressedRadius,
                mPressedLeftPadding,
                mPressedTopPadding,
                mPressedRightPadding,
                mPressedBottomPadding,
                mPressedPadding,
                mPressedColor,
                mPressedStrokeColor,
                mPressedStrokeWidth,
                mPressedDashWidth,
                mPressedDashGap
            )
            pressedDrawable?.apply {
                drawable.addState(intArrayOf(android.R.attr.state_pressed), this)
            }

            val noEnableDrawable = getShape(
                mNoEnableLeftTopRadius,
                mNoEnableRightTopRadius,
                mNoEnableLeftBottomRadius,
                mNoEnableRightBottomRadius,
                mNoEnableRadius,
                mNoEnableLeftPadding,
                mNoEnableTopPadding,
                mNoEnableRightPadding,
                mNoEnableBottomPadding,
                mNoEnablePadding,
                mNoEnableColor,
                mNoEnableStrokeColor,
                mNoEnableStrokeWidth,
                mNoEnableDashWidth,
                mNoEnableDashGap
            )

            noEnableDrawable?.apply {
                drawable.addState(intArrayOf(-android.R.attr.state_enabled), this)
            }

            val focusDrawable = getShape(
                mFocusLeftTopRadius,
                mFocusRightTopRadius,
                mFocusLeftBottomRadius,
                mFocusRightBottomRadius,
                mFocusRadius,
                mFocusLeftPadding,
                mFocusTopPadding,
                mFocusRightPadding,
                mFocusBottomPadding,
                mFocusPadding,
                mFocusColor,
                mFocusStrokeColor,
                mFocusStrokeWidth,
                mFocusDashWidth,
                mFocusDashGap
            )

            focusDrawable?.apply {
                drawable.addState(intArrayOf(android.R.attr.state_focused), this)
            }

            val selectedDrawable = getShape(
                mSelectedLeftTopRadius,
                mSelectedRightTopRadius,
                mSelectedLeftBottomRadius,
                mSelectedRightBottomRadius,
                mSelectedRadius,
                mSelectedLeftPadding,
                mSelectedTopPadding,
                mSelectedRightPadding,
                mSelectedBottomPadding,
                mSelectedPadding,
                mSelectedColor,
                mSelectedStrokeColor,
                mSelectedStrokeWidth,
                mSelectedDashWidth,
                mSelectedDashGap
            )

            selectedDrawable?.apply {
                drawable.addState(intArrayOf(android.R.attr.state_selected), this)
            }

            val normalDrawable = getShape(
                mNormalLeftTopRadius,
                mNormalRightTopRadius,
                mNormalLeftBottomRadius,
                mNormalRightBottomRadius,
                mNormalRadius,
                mNormalLeftPadding,
                mNormalTopPadding,
                mNormalRightPadding,
                mNormalBottomPadding,
                mNormalPadding,
                mNormalColor,
                mNormalStrokeColor,
                mNormalStrokeWidth,
                mNormalDashWidth,
                mNormalDashGap
            )
            if (normalDrawable == null) {
                drawable.addState(intArrayOf(), background)
            } else {
                drawable.addState(intArrayOf(), normalDrawable)
            }
            setBackgroundKeepPadding(drawable)
        }
    }

    private fun hasShape(
        leftTopRadius: Int,
        rightTopRadius: Int,
        leftBottomRadius: Int,
        rightBottomRadius: Int,
        radius: Int,
        leftPadding: Int,
        topPadding: Int,
        rightPadding: Int,
        bottomPadding: Int,
        padding: Int,
        @ColorInt color: Int,
        @ColorInt strokeColor: Int,
        strokeWidth: Int,
        dashWidth: Int,
        dashGap: Int
    ): Boolean {
        if (leftTopRadius == 0 && rightTopRadius == 0 && leftBottomRadius == 0 && rightBottomRadius == 0
            && radius == 0 && leftPadding == 0 && topPadding == 0 && rightPadding == 0 && bottomPadding == 0
            && padding == 0 && color == 0 && strokeColor == 0 && strokeWidth == 0 && dashWidth == 0
            && dashGap == 0
        ) {
            return false
        }
        return true
    }

    private fun getShape(
        leftTopRadius: Int,
        rightTopRadius: Int,
        leftBottomRadius: Int,
        rightBottomRadius: Int,
        radius: Int,
        leftPadding: Int,
        topPadding: Int,
        rightPadding: Int,
        bottomPadding: Int,
        padding: Int,
        @ColorInt color: Int,
        @ColorInt strokeColor: Int,
        strokeWidth: Int,
        dashWidth: Int,
        dashGap: Int
    ): Drawable? {
        if (!hasShape(
                leftTopRadius,
                rightTopRadius,
                leftBottomRadius,
                rightBottomRadius,
                radius,
                leftPadding,
                topPadding,
                rightPadding,
                bottomPadding,
                padding,
                color,
                strokeColor,
                strokeWidth,
                dashWidth,
                dashGap
            )
        ) {
            return null
        }
        var drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE

        if (radius == 0) {
            drawable.cornerRadii = floatArrayOf(
                leftTopRadius.toFloat(),
                leftTopRadius.toFloat(),
                rightTopRadius.toFloat(),
                rightTopRadius.toFloat(),
                rightBottomRadius.toFloat(),
                rightBottomRadius.toFloat(),
                leftBottomRadius.toFloat(),
                leftBottomRadius.toFloat()
            )
        } else {
            drawable.cornerRadius = radius.toFloat()
        }
        if (padding == 0) {
            if (Build.VERSION.SDK_INT >= 29) {
                drawable.setPadding(leftPadding, topPadding, rightPadding, bottomPadding)
            } else {
                drawable.setBounds(leftPadding, topPadding, rightPadding, bottomPadding)
            }
        } else {
            if (Build.VERSION.SDK_INT >= 29) {
                drawable.setPadding(padding, padding, padding, padding)
            } else {
                drawable.setBounds(padding, padding, padding, padding)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.color = ColorStateList.valueOf(color)
        } else {
            drawable.setColor(color)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setStroke(
                strokeWidth,
                ColorStateList.valueOf(strokeColor),
                dashWidth.toFloat(),
                dashGap.toFloat()
            )
        } else {
            drawable.setStroke(strokeWidth, strokeColor, dashWidth.toFloat(), dashGap.toFloat())
        }
        return drawable
    }
}