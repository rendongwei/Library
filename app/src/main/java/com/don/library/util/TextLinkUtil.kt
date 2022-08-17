package com.don.library.util

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView

object TextLinkUtil {
    /**
     * 添加文本超链接
     *
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @param textView
     * 文本填充的TextView
     * @param text
     * 原始文本内容
     * @param listener
     * 超链接单击监听事件
     */
    /**
     * 添加文本超链接
     *
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @param textView
     * 文本填充的TextView
     * @param text
     * 原始文本内容
     */
    @JvmOverloads
    fun addTextLink(
        textView: TextView?, text: CharSequence,
        listener: View.OnClickListener? = null
    ) {
        addTextLink(textView, text, -1, 0, text.length, true, listener)
    }
    /**
     * 添加文本超链接
     *
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @param textView
     * 文本填充的TextView
     * @param text
     * 原始文本内容
     * @param color
     * 超链接文本的颜色
     * @param listener
     * 超链接单击监听事件
     */
    /**
     * 添加文本超链接
     *
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @see {@link .addTextLink
     * @param textView
     * 文本填充的TextView
     * @param text
     * 原始文本内容
     * @param color
     * 超链接文本的颜色
     */
    @JvmOverloads
    fun addTextLink(
        textView: TextView?, text: CharSequence,
        color: Int, listener: View.OnClickListener? = null
    ) {
        addTextLink(textView, text, color, 0, text.length, true, listener)
    }

    /**
     * 添加文本超链接
     *
     *  * TextView为空时不做任何操作
     *  * 文本为空、起始位置或者结束位置不合法时设置当前文本内容
     *
     *
     * @param textView
     * 文本填充的TextView
     * @param text
     * 原始文本内容
     * @param color
     * 超链接文本的颜色
     * @param start
     * 超链接起始位置
     * @param end
     * 超链接结束位置
     * @param showUnderline
     * 是否显示下划线
     * @param listener
     * 超链接单击监听事件
     */
    fun addTextLink(
        textView: TextView?, text: CharSequence,
        color: Int, start: Int, end: Int, showUnderline: Boolean,
        listener: View.OnClickListener?
    ) {
        if (textView == null) {
            return
        }
        if (text.isNullOrEmpty()) {
            textView.text = text
            return
        }
        if (start < 0 || start > text.length || start > end || end > text.length) {
            textView.text = text
            return
        }
        val sp = SpannableString(text)
        sp.setSpan(
            IntentSpan(listener, showUnderline), start, end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        if (color > 0) {
            sp.setSpan(
                ForegroundColorSpan(color), start, end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textView.text = sp
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    class IntentSpan(
        private val mOnClickListener: View.OnClickListener?,
        private val mShowUnderline: Boolean
    ) : ClickableSpan() {
        override fun onClick(view: View) {
            mOnClickListener?.onClick(view)
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = mShowUnderline
        }
    }
}