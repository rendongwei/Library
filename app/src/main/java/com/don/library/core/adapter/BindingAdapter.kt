package com.don.library.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindingAdapter<T, R : ViewDataBinding> constructor(
    context: Context,
    list: MutableList<T>?
) :
    BaseAdapter<T, Any?>(context, list) {

    // 获取布局
    @LayoutRes
    abstract fun getContentView(): Int

    // 数据绑定
    abstract fun bindView(binding: R, position: Int, t: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var binding =
            DataBindingUtil.inflate<R>(
                LayoutInflater.from(mContext),
                getContentView(),
                parent,
                false
            )
        return BindingViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var bindingViewHolder = holder as BindingViewHolder<R>
        bindView(bindingViewHolder.binding, position, getItem(position)!!)
        bindingViewHolder.binding?.executePendingBindings()
    }

    class BindingViewHolder<R : ViewDataBinding>(var binding: R) :
        RecyclerView.ViewHolder(binding.root)
}