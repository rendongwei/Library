package com.don.library.weight.shape.helper

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import com.don.library.R
import com.don.library.weight.shape.interfaces.ILayout
import java.lang.ref.WeakReference
import kotlin.math.floor


class LayoutHelper : ILayout {

    private var mContext: Context? = null

    private val RADIUS_OF_HALF_VIEW_HEIGHT = -1
    private val RADIUS_OF_HALF_VIEW_WIDTH = -2

    private var mWidthLimit: Int = 0
    private var mHeightLimit: Int = 0
    private var mWidthMini: Int = 0
    private var mHeightMini: Int = 0

    private var mTopDividerHeight: Int = 0
    private var mTopDividerInsetLeft: Int = 0
    private var mTopDividerInsetRight: Int = 0
    private var mTopDividerColor: Int = 0
    private var mTopDividerAlpha: Int = 255

    private var mBottomDividerHeight: Int = 0
    private var mBottomDividerInsetLeft: Int = 0
    private var mBottomDividerInsetRight: Int = 0
    private var mBottomDividerColor: Int = 0
    private var mBottomDividerAlpha: Int = 255

    private var mLeftDividerWidth: Int = 0
    private var mLeftDividerInsetTop: Int = 0
    private var mLeftDividerInsetBottom: Int = 0
    private var mLeftDividerColor: Int = 0
    private var mLeftDividerAlpha: Int = 255

    private var mRightDividerWidth: Int = 0
    private var mRightDividerInsetTop: Int = 0
    private var mRightDividerInsetBottom: Int = 0
    private var mRightDividerColor: Int = 0
    private var mRightDividerAlpha: Int = 255
    private var mDividerPaint: Paint? = null

    private var mClipPaint: Paint? = null
    private var mMode: PorterDuffXfermode? = null
    private var mRadius: Int = 0

    @ILayout.HideRadiusSide
    private var mHideRadiusSide: Int = ILayout.HIDE_RADIUS_SIDE_NONE
    private var mRadiusArray: FloatArray? = null
    private var mShouldUseRadiusArray: Boolean = false
    private var mBorderRect: RectF? = null
    private var mBorderColor: Int = 0
    private var mBorderWidth: Int = 1
    private var mOuterNormalColor: Int = 0
    private var mOwner: WeakReference<View>? = null
    private var mIsOutlineExcludePadding: Boolean = false
    private var mPath: Path = Path()

    private var mIsShowBorderOnlyBeforeL: Boolean = true
    private var mShadowElevation: Int = 0
    private var mShadowAlpha: Float = 0.0f
    private var mShadowColor: Int = Color.BLACK

    private var mOutlineInsetLeft: Int = 0
    private var mOutlineInsetRight: Int = 0
    private var mOutlineInsetTop: Int = 0
    private var mOutlineInsetBottom: Int = 0

    companion object {
        fun useFeature() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    override fun initLayout(owner: View, attrs: AttributeSet?) {
        mContext = owner.context
        mOwner = WeakReference(owner)
        mMode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        mClipPaint = Paint()
        mClipPaint?.isAntiAlias = true
        mBorderRect = RectF()

        if (attrs != null) {
            var typedArray = mContext!!.obtainStyledAttributes(
                attrs,
                R.styleable.Layout,
                0,
                0
            )
            mWidthLimit = typedArray.getDimensionPixelSize(R.styleable.Layout_android_maxWidth, 0)
            mHeightLimit = typedArray.getDimensionPixelSize(R.styleable.Layout_android_maxHeight, 0)
            mWidthMini = typedArray.getDimensionPixelSize(R.styleable.Layout_android_minWidth, 0)
            mHeightLimit = typedArray.getDimensionPixelSize(R.styleable.Layout_android_minHeight, 0)

            mBottomDividerHeight = typedArray.getDimensionPixelSize(
                R.styleable.Layout_bottomDividerHeight,
                0
            )
            mBottomDividerColor = typedArray.getColor(R.styleable.Layout_bottomDividerColor, 0)
            mBottomDividerInsetLeft = typedArray.getDimensionPixelSize(
                R.styleable.Layout_bottomDividerInsetLeft,
                0
            )
            mBottomDividerInsetRight = typedArray.getDimensionPixelSize(
                R.styleable.Layout_bottomDividerInsetRight,
                0
            )

            mTopDividerHeight = typedArray.getDimensionPixelSize(
                R.styleable.Layout_topDividerHeight,
                0
            )
            mTopDividerColor = typedArray.getColor(R.styleable.Layout_topDividerColor, 0)
            mTopDividerInsetLeft = typedArray.getDimensionPixelSize(
                R.styleable.Layout_topDividerInsetLeft,
                0
            )
            mTopDividerInsetRight = typedArray.getDimensionPixelSize(
                R.styleable.Layout_topDividerInsetRight,
                0
            )

            mLeftDividerWidth = typedArray.getDimensionPixelSize(
                R.styleable.Layout_leftDividerWidth,
                0
            )
            mLeftDividerColor = typedArray.getColor(R.styleable.Layout_leftDividerColor, 0)
            mLeftDividerInsetTop = typedArray.getDimensionPixelSize(
                R.styleable.Layout_leftDividerInsetTop,
                0
            )
            mLeftDividerInsetBottom = typedArray.getDimensionPixelSize(
                R.styleable.Layout_leftDividerInsetBottom,
                0
            )

            mRightDividerWidth = typedArray.getDimensionPixelSize(
                R.styleable.Layout_rightDividerWidth,
                0
            )
            mRightDividerColor = typedArray.getColor(R.styleable.Layout_rightDividerColor, 0)
            mRightDividerInsetTop = typedArray.getDimensionPixelSize(
                R.styleable.Layout_rightDividerInsetTop,
                0
            )
            mRightDividerInsetBottom = typedArray.getDimensionPixelSize(
                R.styleable.Layout_rightDividerInsetBottom,
                0
            )

            mRadius = typedArray.getDimensionPixelSize(R.styleable.Layout_radius, 0)
            mBorderColor = typedArray.getColor(R.styleable.Layout_borderColor, 0)
            mBorderWidth = typedArray.getDimensionPixelSize(R.styleable.Layout_borderWidth, 1)
            mOuterNormalColor = typedArray.getColor(R.styleable.Layout_outerNormalColor, 0)
            mHideRadiusSide = typedArray.getInt(
                R.styleable.Layout_hideRadiusSide,
                ILayout.HIDE_RADIUS_SIDE_NONE
            )
            mIsShowBorderOnlyBeforeL = typedArray.getBoolean(
                R.styleable.Layout_showBorderOnlyBeforeL,
                true
            )
            mShadowElevation = typedArray.getDimensionPixelSize(
                R.styleable.Layout_shadowElevation,
                0
            )
            mShadowColor = typedArray.getColor(R.styleable.Layout_shadowColor, Color.BLACK)
            mShadowAlpha = typedArray.getFloat(R.styleable.Layout_shadowAlpha, 0.0f)

            mOutlineInsetTop = typedArray.getDimensionPixelSize(
                R.styleable.Layout_outlineInsetTop,
                0
            )
            mOutlineInsetLeft = typedArray.getDimensionPixelSize(
                R.styleable.Layout_outlineInsetLeft,
                0
            )
            mOutlineInsetRight = typedArray.getDimensionPixelSize(
                R.styleable.Layout_outlineInsetRight,
                0
            )
            mOutlineInsetBottom = typedArray.getDimensionPixelSize(
                R.styleable.Layout_outlineInsetBottom,
                0
            )
            mIsOutlineExcludePadding = typedArray.getBoolean(
                R.styleable.Layout_outlineExcludePadding,
                false
            )

            typedArray.recycle()
        }
        setRadiusAndShadow(mRadius, mHideRadiusSide, mShadowElevation, mShadowAlpha)
    }

    override fun setWidthLimit(widthLimit: Int): Boolean {
        if (mWidthLimit != widthLimit) {
            mWidthLimit = widthLimit
            mOwner?.get()?.apply {
                requestLayout()
                invalidate()
            }
            return true
        }
        return false
    }

    override fun setHeightLimit(heightLimit: Int): Boolean {
        if (mHeightLimit != heightLimit) {
            mHeightLimit = heightLimit
            mOwner?.get()?.apply {
                requestLayout()
                invalidate()
            }
            return true
        }
        return false
    }

    override fun setOutlineExcludePadding(outlineExcludePadding: Boolean) {
        if (useFeature()) {
            mOwner?.get()?.apply {
                mIsOutlineExcludePadding = outlineExcludePadding
                invalidateOutline()
            }
        }
    }

    override fun setShadowElevation(elevation: Int) {
        if (mShadowElevation == elevation) {
            return
        }
        mShadowElevation = elevation
        invalidateOutline()
    }

    override fun getShadowElevation(): Int {
        return mShadowElevation
    }

    override fun setShadowAlpha(shadowAlpha: Float) {
        if (mShadowAlpha == shadowAlpha || shadowAlpha !in 0.0f..255.0f) {
            return
        }
        mShadowAlpha = shadowAlpha
        invalidateOutline()
    }

    override fun getShadowAlpha(): Float {
        return mShadowAlpha
    }

    override fun setShadowColor(shadowColor: Int) {
        if (mShadowColor == shadowColor) {
            return
        }
        mShadowColor = shadowColor
        setShadowColorInner(mShadowColor)
    }

    override fun getShadowColor(): Int {
        return mShadowColor
    }

    override fun setRadius(radius: Int) {
        if (mRadius == radius) {
            return
        }
        mRadius = radius
        setRadiusAndShadow(mRadius, mShadowElevation, mShadowAlpha)
    }

    override fun setRadius(radius: Int, hideRadiusSide: Int) {
        if (mRadius == radius && mHideRadiusSide == hideRadiusSide) {
            return
        }
        setRadiusAndShadow(mRadius, mHideRadiusSide, mShadowElevation, mShadowAlpha)
    }

    override fun getRadius(): Int {
        return mRadius
    }

    override fun setOutlineInset(left: Int, top: Int, right: Int, bottom: Int) {
        if (useFeature()) {
            mOwner?.get()?.apply {
                mOutlineInsetLeft = left
                mOutlineInsetTop = top
                mOutlineInsetRight = right
                mOutlineInsetBottom = bottom
                invalidateOutline()
            }
        }
    }

    override fun setShowBorderOnlyBeforeL(showBorderOnlyBeforeL: Boolean) {
        mIsShowBorderOnlyBeforeL = showBorderOnlyBeforeL
        invalidate()
    }

    override fun setHideRadiusSide(hideRadiusSide: Int) {
        if (mHideRadiusSide == hideRadiusSide) {
            return
        }
        mHideRadiusSide = hideRadiusSide
        setRadiusAndShadow(mRadius, mHideRadiusSide, mShadowElevation, mShadowAlpha)
    }

    override fun getHideRadiusSide(): Int {
        return mHideRadiusSide
    }

    override fun setRadiusAndShadow(radius: Int, shadowElevation: Int, shadowAlpha: Float) {
        setRadiusAndShadow(radius, mHideRadiusSide, shadowElevation, shadowAlpha)
    }

    override fun setRadiusAndShadow(
        radius: Int, @ILayout.HideRadiusSide hideRadiusSide: Int,
        shadowElevation: Int,
        shadowAlpha: Float
    ) {
        setRadiusAndShadow(radius, hideRadiusSide, shadowElevation, mShadowColor, shadowAlpha)
    }

    override fun setRadiusAndShadow(
        radius: Int,
        @ILayout.HideRadiusSide hideRadiusSide: Int,
        shadowElevation: Int,
        shadowColor: Int,
        shadowAlpha: Float
    ) {
        mOwner?.get()?.apply {
            mRadius = radius
            mHideRadiusSide = hideRadiusSide
            mShadowElevation = shadowElevation
            mShadowColor = shadowColor
            mShadowAlpha = shadowAlpha
            mShouldUseRadiusArray = isRadiusWithSideHidden()
            if (useFeature()) {
                elevation = if (mShadowElevation == 0 || mShouldUseRadiusArray) {
                    0.0f
                } else {
                    mShadowElevation.toFloat()
                }
                setShadowColorInner(mShadowColor)
                outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(v: View?, outline: Outline?) {
                        var width = v?.width ?: 0
                        var height = v?.height ?: 0
                        if (width == 0 || height == 0) {
                            return
                        }
                        var radius = getRealRadius()
                        val min: Int = Math.min(width, height)
                        if (radius * 2 > min) {
                            // 解决 OnePlus 3T 8.0 上显示变形
                            radius = (min / 2f).toInt()
                        }
                        if (mShouldUseRadiusArray) {
                            var left = 0
                            var top = 0
                            var right = width
                            var bottom = height
                            when (mHideRadiusSide) {
                                ILayout.HIDE_RADIUS_SIDE_LEFT -> left -= radius
                                ILayout.HIDE_RADIUS_SIDE_TOP -> top -= radius
                                ILayout.HIDE_RADIUS_SIDE_RIGHT -> right += radius
                                ILayout.HIDE_RADIUS_SIDE_BOTTOM -> bottom += radius
                            }
                            outline?.setRoundRect(left, top, right, bottom, radius.toFloat())
                            return
                        }

                        var left = mOutlineInsetLeft
                        var top = mOutlineInsetTop
                        var right = width - mOutlineInsetRight
                        var bottom = Math.max(top + 1, height - mOutlineInsetBottom)
                        if (mIsOutlineExcludePadding) {
                            left += v!!.paddingLeft
                            top += v!!.paddingTop
                            right = Math.max(left + 1, right - v!!.paddingRight)
                            bottom = Math.max(top + 1, bottom - v!!.paddingBottom)
                        }
                        var shadowAlpha = mShadowAlpha
                        if (mShadowElevation == 0) {
                            shadowAlpha = 1.0f
                        }
                        outline?.alpha = shadowAlpha
                        if (radius <= 0) {
                            outline?.setRect(left, top, right, bottom)
                        } else {
                            outline?.setRoundRect(left, top, right, bottom, radius.toFloat())
                        }
                    }
                }
                clipToOutline =
                    mRadius == RADIUS_OF_HALF_VIEW_WIDTH || mRadius == RADIUS_OF_HALF_VIEW_HEIGHT || mRadius > 0
            }
            invalidate()
        }
    }

    override fun setBorderColor(borderColor: Int) {
        mBorderColor = borderColor
        invalidate()
    }

    override fun setBorderWidth(borderWidth: Int) {
        mBorderWidth = borderWidth
        invalidate()
    }

    override fun updateTopDivider(
        topInsetLeft: Int,
        topInsetRight: Int,
        topDividerHeight: Int,
        topDividerColor: Int
    ) {
        mTopDividerInsetLeft = topInsetLeft
        mTopDividerInsetRight = topInsetRight
        mTopDividerHeight = topDividerHeight
        mTopDividerColor = topDividerColor
        invalidate()
    }

    override fun updateBottomDivider(
        bottomInsetLeft: Int,
        bottomInsetRight: Int,
        bottomDividerHeight: Int,
        bottomDividerColor: Int
    ) {
        mBottomDividerInsetLeft = bottomInsetLeft
        mBottomDividerInsetRight = bottomInsetRight
        mBottomDividerColor = bottomDividerColor
        mBottomDividerHeight = bottomDividerHeight
        invalidate()
    }

    override fun updateLeftDivider(
        leftInsetTop: Int,
        leftInsetBottom: Int,
        leftDividerWidth: Int,
        leftDividerColor: Int
    ) {
        mLeftDividerInsetTop = leftInsetTop
        mLeftDividerInsetBottom = leftInsetBottom
        mLeftDividerWidth = leftDividerWidth
        mLeftDividerColor = leftDividerColor
        invalidate()
    }

    override fun updateRightDivider(
        rightInsetTop: Int,
        rightInsetBottom: Int,
        rightDividerWidth: Int,
        rightDividerColor: Int
    ) {
        mRightDividerInsetTop = rightInsetTop
        mRightDividerInsetBottom = rightInsetBottom
        mRightDividerWidth = rightDividerWidth
        mRightDividerColor = rightDividerColor
        invalidate()
    }

    override fun onlyShowTopDivider(
        topInsetLeft: Int,
        topInsetRight: Int,
        topDividerHeight: Int,
        topDividerColor: Int
    ) {
        updateTopDivider(topInsetLeft, topInsetRight, topDividerHeight, topDividerColor)
        mLeftDividerWidth = 0
        mRightDividerWidth = 0
        mBottomDividerHeight = 0
        invalidate()
    }

    override fun onlyShowBottomDivider(
        bottomInsetLeft: Int,
        bottomInsetRight: Int,
        bottomDividerHeight: Int,
        bottomDividerColor: Int
    ) {
        updateBottomDivider(
            bottomInsetLeft,
            bottomInsetRight,
            bottomDividerHeight,
            bottomDividerColor
        )
        mLeftDividerWidth = 0
        mRightDividerWidth = 0
        mTopDividerHeight = 0
        invalidate()
    }

    override fun onlyShowLeftDivider(
        leftInsetTop: Int,
        leftInsetBottom: Int,
        leftDividerWidth: Int,
        leftDividerColor: Int
    ) {
        updateLeftDivider(leftInsetTop, leftInsetBottom, leftDividerWidth, leftDividerColor)
        mRightDividerWidth = 0
        mTopDividerHeight = 0
        mBottomDividerHeight = 0
        invalidate()
    }

    override fun onlyShowRightDivider(
        rightInsetTop: Int,
        rightInsetBottom: Int,
        rightDividerWidth: Int,
        rightDividerColor: Int
    ) {
        updateRightDivider(rightInsetTop, rightInsetBottom, rightDividerWidth, rightDividerColor)
        mLeftDividerWidth = 0
        mTopDividerHeight = 0
        mBottomDividerHeight = 0
        invalidate()
    }

    override fun setTopDividerAlpha(dividerAlpha: Int) {
        mTopDividerAlpha = dividerAlpha
        invalidate()
    }

    override fun setBottomDividerAlpha(dividerAlpha: Int) {
        mBottomDividerAlpha = dividerAlpha
        invalidate()
    }

    override fun setLeftDividerAlpha(dividerAlpha: Int) {
        mLeftDividerAlpha = dividerAlpha
        invalidate()
    }

    override fun setRightDividerAlpha(dividerAlpha: Int) {
        mRightDividerAlpha = dividerAlpha
        invalidate()
    }

    override fun setOuterNormalColor(color: Int) {
        if (mOuterNormalColor == color) {
            return
        }
        mOuterNormalColor = color
        invalidate()
    }

    override fun updateLeftSeparatorColor(color: Int) {
        if (mLeftDividerColor == color) {
            return
        }
        mLeftDividerColor = color
        invalidate()
    }

    override fun updateRightSeparatorColor(color: Int) {
        if (mRightDividerColor == color) {
            return
        }
        mRightDividerColor = color
        invalidate()
    }

    override fun updateTopSeparatorColor(color: Int) {
        if (mTopDividerColor == color) {
            return
        }
        mTopDividerColor = color
        invalidate()
    }

    override fun updateBottomSeparatorColor(color: Int) {
        if (mBottomDividerColor == color) {
            return
        }
        mBottomDividerColor = color
        invalidate()
    }

    override fun hasTopSeparator(): Boolean {
        return mTopDividerHeight > 0
    }

    override fun hasRightSeparator(): Boolean {
        return mRightDividerWidth > 0
    }

    override fun hasLeftSeparator(): Boolean {
        return mLeftDividerWidth > 0
    }

    override fun hasBottomSeparator(): Boolean {
        return mBottomDividerHeight > 0
    }

    override fun hasBorder(): Boolean {
        return mBorderWidth > 0
    }


    fun handleMiniWidth(widthMeasureSpec: Int, measuredWidth: Int): Int {
        if (View.MeasureSpec.getMode(widthMeasureSpec) != View.MeasureSpec.EXACTLY && measuredWidth < mWidthMini) {
            return View.MeasureSpec.makeMeasureSpec(mWidthMini, View.MeasureSpec.EXACTLY)
        }
        return widthMeasureSpec
    }

    fun handleMiniHeight(heightMeasureSpec: Int, measuredHeight: Int): Int {
        return if (View.MeasureSpec.getMode(heightMeasureSpec) != View.MeasureSpec.EXACTLY && measuredHeight < mHeightMini) {
            View.MeasureSpec.makeMeasureSpec(mHeightMini, View.MeasureSpec.EXACTLY)
        } else heightMeasureSpec
    }

    fun getMeasuredWidthSpec(widthMeasureSpec: Int): Int {
        var widthMeasureSpec = widthMeasureSpec
        if (mWidthLimit > 0) {
            val size = View.MeasureSpec.getSize(widthMeasureSpec)
            if (size > mWidthLimit) {
                val mode = View.MeasureSpec.getMode(widthMeasureSpec)
                widthMeasureSpec = if (mode == View.MeasureSpec.AT_MOST) {
                    View.MeasureSpec.makeMeasureSpec(mWidthLimit, View.MeasureSpec.AT_MOST)
                } else {
                    View.MeasureSpec.makeMeasureSpec(mWidthLimit, View.MeasureSpec.EXACTLY)
                }

            }
        }
        return widthMeasureSpec
    }

    fun getMeasuredHeightSpec(heightMeasureSpec: Int): Int {
        var heightMeasureSpec = heightMeasureSpec
        if (mHeightLimit > 0) {
            val size = View.MeasureSpec.getSize(heightMeasureSpec)
            if (size > mHeightLimit) {
                val mode = View.MeasureSpec.getMode(heightMeasureSpec)
                heightMeasureSpec = if (mode == View.MeasureSpec.AT_MOST) {
                    View.MeasureSpec.makeMeasureSpec(mWidthLimit, View.MeasureSpec.AT_MOST)
                } else {
                    View.MeasureSpec.makeMeasureSpec(mWidthLimit, View.MeasureSpec.EXACTLY)
                }
            }
        }
        return heightMeasureSpec
    }

    override fun drawDividers(canvas: Canvas, w: Int, h: Int) {
        mOwner?.get()?.apply {
            if (mDividerPaint == null && (mLeftDividerWidth > 0 || mTopDividerHeight > 0 || mRightDividerWidth > 0 || mBottomDividerHeight > 0)) {
                mDividerPaint = Paint()
            }
            canvas.save()
            canvas.translate(scrollX.toFloat(), scrollY.toFloat())
            if (mTopDividerHeight > 0) {
                mDividerPaint?.strokeWidth = mTopDividerHeight.toFloat()
                mDividerPaint?.color = mTopDividerColor
                if (mTopDividerAlpha < 255) {
                    mDividerPaint?.alpha = mTopDividerAlpha
                }
                val y = mTopDividerHeight / 2.0f
                canvas.drawLine(
                    mTopDividerInsetLeft.toFloat(),
                    y,
                    w - mTopDividerInsetRight.toFloat(),
                    y,
                    mDividerPaint!!
                )
            }
            if (mBottomDividerHeight > 0) {
                mDividerPaint?.strokeWidth = mBottomDividerHeight.toFloat()
                mDividerPaint?.color = mBottomDividerColor
                if (mBottomDividerAlpha < 255) {
                    mDividerPaint?.alpha = mBottomDividerAlpha
                }
                val y = floor(h - mBottomDividerHeight / 2f)
                canvas.drawLine(
                    mBottomDividerInsetLeft.toFloat(),
                    y,
                    w - mBottomDividerInsetRight.toFloat(),
                    y,
                    mDividerPaint!!
                )
            }
            if (mLeftDividerWidth > 0) {
                mDividerPaint?.strokeWidth = mLeftDividerWidth.toFloat()
                mDividerPaint?.color = mLeftDividerColor
                if (mLeftDividerAlpha < 255) {
                    mDividerPaint?.alpha = mLeftDividerAlpha
                }
                val x = mLeftDividerWidth / 2.0f
                canvas.drawLine(
                    x,
                    mLeftDividerInsetTop.toFloat(),
                    x,
                    h - mLeftDividerInsetBottom.toFloat(),
                    mDividerPaint!!
                )
            }
            if (mRightDividerWidth > 0) {
                mDividerPaint?.strokeWidth = mRightDividerWidth.toFloat()
                mDividerPaint?.color = mRightDividerColor
                if (mRightDividerWidth < 255) {
                    mDividerPaint?.alpha = mRightDividerWidth
                }
                val x = floor(w - mRightDividerWidth / 2f)
                canvas.drawLine(
                    x,
                    mRightDividerInsetTop.toFloat(),
                    x,
                    h - mRightDividerInsetBottom.toFloat(),
                    mDividerPaint!!
                )
            }
            canvas.restore()
        }
    }

    override fun dispatchRoundBorderDraw(canvas: Canvas) {
        mOwner?.get()?.apply {
            var radius = getRealRadius()
            var needCheckFakeOuterNormalDraw = radius > 0 && !useFeature() && mOuterNormalColor != 0
            var needDrawBorder = mBorderWidth > 0 && mBorderColor != 0
            if (!needCheckFakeOuterNormalDraw && !needDrawBorder) {
                return
            }
            if (mIsShowBorderOnlyBeforeL && useFeature() && mShadowElevation != 0) {
                return
            }
            var width = canvas.width
            var height = canvas.height
            canvas.save()
            canvas.translate(scrollX.toFloat(), scrollY.toFloat())
            var halfBorderWidth = mBorderWidth / 2.0f
            if (mIsOutlineExcludePadding) {
                mBorderRect?.set(
                    paddingLeft + halfBorderWidth,
                    paddingTop + halfBorderWidth,
                    width - paddingRight - halfBorderWidth,
                    height - paddingBottom - halfBorderWidth
                )
            } else {
                mBorderRect?.set(
                    halfBorderWidth,
                    halfBorderWidth,
                    width - halfBorderWidth,
                    height - halfBorderWidth
                )
            }
            mShouldUseRadiusArray = isRadiusWithSideHidden()
            if (mShouldUseRadiusArray) {
                mRadiusArray = floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f)
                when (mHideRadiusSide) {
                    ILayout.HIDE_RADIUS_SIDE_TOP -> {
                        mRadiusArray!![4] = radius.toFloat()
                        mRadiusArray!![5] = radius.toFloat()
                        mRadiusArray!![6] = radius.toFloat()
                        mRadiusArray!![7] = radius.toFloat()
                    }
                    ILayout.HIDE_RADIUS_SIDE_RIGHT -> {
                        mRadiusArray!![0] = radius.toFloat()
                        mRadiusArray!![1] = radius.toFloat()
                        mRadiusArray!![6] = radius.toFloat()
                        mRadiusArray!![7] = radius.toFloat()
                    }
                    ILayout.HIDE_RADIUS_SIDE_BOTTOM -> {
                        mRadiusArray!![0] = radius.toFloat()
                        mRadiusArray!![1] = radius.toFloat()
                        mRadiusArray!![2] = radius.toFloat()
                        mRadiusArray!![3] = radius.toFloat()
                    }
                    ILayout.HIDE_RADIUS_SIDE_LEFT -> {
                        mRadiusArray!![2] = radius.toFloat()
                        mRadiusArray!![3] = radius.toFloat()
                        mRadiusArray!![4] = radius.toFloat()
                        mRadiusArray!![5] = radius.toFloat()
                    }
                }
            }
            if (needCheckFakeOuterNormalDraw) {
                var layerId =
                    canvas.saveLayer(
                        0.0f,
                        0.0f,
                        width.toFloat(),
                        height.toFloat(),
                        null,
                        Canvas.ALL_SAVE_FLAG
                    )
                canvas.drawColor(mOuterNormalColor)
                mClipPaint?.color = mOuterNormalColor
                mClipPaint?.style = Paint.Style.FILL
                mClipPaint?.xfermode = mMode
                if (mShouldUseRadiusArray) {
                    drawRoundRect(canvas, mBorderRect!!, mRadiusArray!!, mClipPaint!!)
                } else {
                    canvas.drawRoundRect(
                        mBorderRect!!,
                        radius.toFloat(),
                        radius.toFloat(),
                        mClipPaint!!
                    )
                }
                mClipPaint?.xfermode = null
                canvas.restoreToCount(layerId)
            }
            if (needDrawBorder) {
                mClipPaint?.color = mBorderColor
                mClipPaint?.strokeWidth = mBorderWidth.toFloat()
                mClipPaint?.style = Paint.Style.STROKE
                when {
                    mShouldUseRadiusArray -> {
                        drawRoundRect(canvas, mBorderRect!!, mRadiusArray!!, mClipPaint!!)
                    }
                    radius <= 0 -> {
                        canvas.drawRect(mBorderRect!!, mClipPaint!!)
                    }
                    else -> {
                        canvas.drawRoundRect(
                            mBorderRect!!,
                            radius.toFloat(),
                            radius.toFloat(),
                            mClipPaint!!
                        )
                    }
                }
            }
            canvas.restore()
        }
    }

    private fun drawRoundRect(canvas: Canvas, rect: RectF, radiusArray: FloatArray, paint: Paint) {
        mPath.reset()
        mPath.addRoundRect(rect, radiusArray, Path.Direction.CW)
        canvas.drawPath(mPath, paint)

    }

    private fun setShadowColorInner(shadowColor: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mOwner?.get()?.apply {
                outlineAmbientShadowColor = shadowColor
                outlineSpotShadowColor = shadowColor
            }
        }
    }

    private fun isRadiusWithSideHidden(): Boolean {
        return (mRadius == RADIUS_OF_HALF_VIEW_HEIGHT ||
                mRadius == RADIUS_OF_HALF_VIEW_WIDTH ||
                mRadius > 0) && mHideRadiusSide != ILayout.HIDE_RADIUS_SIDE_NONE
    }

    private fun getRealRadius(): Int {
        mOwner?.get()?.apply {
            return when (mRadius) {
                RADIUS_OF_HALF_VIEW_WIDTH -> width / 2
                RADIUS_OF_HALF_VIEW_HEIGHT -> height / 2
                else -> mRadius
            }
        }
        return mRadius
    }

    private fun invalidateOutline() {
        if (useFeature()) {
            mOwner?.get()?.apply {
                elevation = if (mShadowElevation <= 0) {
                    0.0f
                } else {
                    mShadowElevation.toFloat()
                }
                invalidateOutline()
            }
        }
    }

    private fun invalidate() {
        mOwner?.get()?.apply {
            invalidate()
        }
    }
}