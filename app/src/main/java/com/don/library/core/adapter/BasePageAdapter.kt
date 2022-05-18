package com.don.library.core.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.don.library.extend.toActivity

/**
 * PageAdapter基类
 */
open abstract class BasePageAdapter<T> constructor(
    // Context上下文
    var mContext: Context,
    // 数据源
    private var mList: MutableList<T>?
) : PagerAdapter() {

    // Activity
    protected val mActivity by lazy {
        mContext.toActivity<AppCompatActivity>()
    }

    abstract fun getView(container: ViewGroup, position: Int): View

    override fun getCount(): Int {
        return mList?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = getView(container, position)
        container.addView(view)
        return view
    }

    // 获取数据源
    fun getList(): MutableList<T>? {
        return mList
    }

    // 获取单个数据对象
    fun getItem(position: Int): T? {
        if (position < 0 || position > count - 1) {
            return null
        }
        return mList?.getOrNull(position)
    }
}