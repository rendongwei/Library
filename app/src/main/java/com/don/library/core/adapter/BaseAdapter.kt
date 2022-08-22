package com.don.library.core.adapter

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.don.library.extend.toActivity

/**
 * Adapter基类
 */
open abstract class BaseAdapter<T, U> constructor(
    // Context上下文
    var mContext: Context,
    // 数据源
    private var mList: MutableList<T>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Activity
    protected val mActivity by lazy {
        mContext.toActivity<AppCompatActivity>()
    }

    protected var mItemClickListener: ((v: View, position: Int, bean: T) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // 获取数量
    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    // 获取单个数据对象
    fun getItem(position: Int): T? {
        if (position < 0 || position > itemCount - 1) {
            return null
        }
        return mList?.getOrNull(position)
    }

    // 获取数据源
    fun getList(): MutableList<T>? {
        return mList
    }

    fun update(list: MutableList<T>) {
        mList?.clear()
        mList?.addAll(list)
        notifyDataSetChanged()
    }

    // 末尾添加一个数据
    fun add(t: T) {
        add(-1, t)
    }

    // 添加一个数据到某个位置
    fun add(index: Int, t: T) {
        addAll(index, mutableListOf(t))
    }

    // 末尾添加一组数据
    fun addAll(t: MutableList<T>) {
        addAll(-1, t)
    }

    // 添加一组数据到某个位置
    fun addAll(index: Int, t: MutableList<T>) {
        mList?.apply {
            var position = when {
                index < 0 || index > lastIndex -> lastIndex + 1
                else -> index
            }
            addAll(position, t)
            notifyItemRangeInserted(position, t.size)
        }
    }

    // 移除某个位置数据
    fun remove(index: Int) {
        mList?.apply {
            if (isEmpty() || index < 0 || index > lastIndex) {
                return
            }
            removeAt(index)
            notifyItemRemoved(index)
            if (itemCount - index > 0) {
                notifyItemRangeChanged(index, itemCount - index)
            }
        }
    }

    // 移除所有数据
    fun removeAll() {
        mList?.apply {
            clear()
            notifyDataSetChanged()
        }
    }

    // 刷新某个数据源
    fun change(index: Int) {
        notifyItemChanged(index)
    }

    fun setOnItemClickListener(listener: ((v: View, position: Int, bean: T) -> Unit)?) {
        mItemClickListener = listener
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
