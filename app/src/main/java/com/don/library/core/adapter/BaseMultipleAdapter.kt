package com.don.library.core.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.don.library.extend.layout

/**
 * 基于BaseAdapter简单封装(适用于多个布局)
 */
abstract class BaseMultipleAdapter<T> constructor(context: Context, list: MutableList<T>?) :
    BaseAdapter<T, Any?>(context, list) {

    // 获取布局 k = viewType , v = layoutId
    abstract fun getContentViews(): Map<Int, Int>

    // 样式类型
    abstract fun getItemType(position: Int): Int

    // 数据绑定
    abstract fun bindView(view: View, position: Int, t: T)

    override fun getItemViewType(position: Int): Int {
        return getItemType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(mContext.layout(getContentViews()[viewType]!!, parent, false))
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