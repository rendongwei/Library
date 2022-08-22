package com.don.library.core.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.don.library.extend.layout

/**
 * 基于BaseAdapter简单封装(适用于单个布局)
 */
abstract class SimpleAdapter<T> constructor(context: Context, list: MutableList<T>?) :
    BaseAdapter<T, Any?>(context, list) {

    // 获取布局
    @LayoutRes
    abstract fun getContentView(): Int

    // 数据绑定
    abstract fun bindView(view: View, position: Int, t: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(mContext.layout(getContentView(), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder.itemView) {
            getItem(position)?.apply {
                bindView(this@with, position, this)
                mItemClickListener?.let { listener ->
                    setOnClickListener {
                        listener.invoke(this@with, position, this)
                    }
                }
            }
        }
    }
}