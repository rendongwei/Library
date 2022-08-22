package com.don.library.core.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.don.library.extend.layout

/**
 * 基于BaseAdapter监听式简单封装(适用于单个布局)
 */
class ListenerAdapter<T> constructor(
    // Context上下文
    context: Context,
    // 布局
    @LayoutRes
    var mContentView: Int,
    // 数据源
    list: MutableList<T>?,
    // 监听
    var mListener: ((view: View, position: Int, t: T) -> Unit)
) :
    BaseAdapter<T, Any?>(context, list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(mContext.layout(mContentView, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder.itemView) {
            getItem(position)?.apply {
                mListener.invoke(this@with, position, this)
                mItemClickListener?.let { listener ->
                    setOnClickListener {
                        listener.invoke(this@with, position, this)
                    }
                }
            }
        }
    }
}