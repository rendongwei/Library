package com.don.library.core.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.don.library.constants.Tag
import com.don.library.extend.layout
import com.don.library.extend.toActivity
import com.don.library.util.LogUtil
import com.don.library.core.mvvm.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import org.greenrobot.eventbus.EventBus

/**
 * Fragment基类
 */
abstract class BaseFragment : Fragment(), CoroutineScope by MainScope() {

    // Application上下文
    protected lateinit var mApplicationContext: Context

    // Activity
    protected lateinit var mActivity: AppCompatActivity

    // Context上下文
    protected lateinit var mContext: Context

    // 当前界面View
    protected var mContentView: View? = null

    // 获取布局
    @LayoutRes
    abstract fun getContentView(): Int

    // 注册监听
    abstract fun initListener()

    // 初始化操作
    abstract fun init()

    // 获取自定义View布局,如果为null则使用getContentView()获取
    open fun getCustomContentView(): View? {
        return null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtil.log(Tag.FRAGMENT, "onAttach: " + javaClass.canonicalName)

        mApplicationContext = context.applicationContext
        mActivity = context.toActivity()
        mContext = context
    }

    open fun setDataBindingView(): View? {
        return null
    }

    open fun isOpenEventBus(): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.log(Tag.FRAGMENT, "onCreate: " + javaClass.canonicalName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtil.log(Tag.FRAGMENT, "onCreateView: " + javaClass.canonicalName)
        mContentView = setDataBindingView()
        if (mContentView == null) {
            mContentView = getCustomContentView() ?: layout(getContentView(), container, false)
        }
        return mContentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtil.log(Tag.FRAGMENT, "onActivityCreated: " + javaClass.canonicalName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtil.log(Tag.FRAGMENT, "onViewCreated: " + javaClass.canonicalName)
        if (isOpenEventBus()) {
            EventBus.getDefault().register(this)
        }
        initListener()
        init()
    }

    override fun onStart() {
        super.onStart()
        LogUtil.log(Tag.FRAGMENT, "onStart: " + javaClass.canonicalName)
    }

    override fun onResume() {
        super.onResume()
        LogUtil.log(Tag.FRAGMENT, "onResume: " + javaClass.canonicalName)
    }

    override fun onPause() {
        super.onPause()
        LogUtil.log(Tag.FRAGMENT, "onPause: " + javaClass.canonicalName)
    }

    override fun onStop() {
        super.onStop()
        LogUtil.log(Tag.FRAGMENT, "onStop: " + javaClass.canonicalName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (isOpenEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        LogUtil.log(Tag.FRAGMENT, "onDestroyView: " + javaClass.canonicalName)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.log(Tag.FRAGMENT, "onDestroy: " + javaClass.canonicalName)
        cancel()
    }

    override fun onDetach() {
        super.onDetach()
        LogUtil.log(Tag.FRAGMENT, "onDetach: " + javaClass.canonicalName)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtil.log(Tag.FRAGMENT, "onSaveInstanceState: " + javaClass.canonicalName)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            LogUtil.log(Tag.FRAGMENT, "onHiddenChanged  hidden: " + javaClass.canonicalName)
        } else {
            LogUtil.log(Tag.FRAGMENT, "onHiddenChanged  show: " + javaClass.canonicalName)
        }
    }

    fun <T : BaseViewModel> createViewModel(cls: Class<T>): T {
        val viewModel =
            ViewModelProvider(mActivity, ViewModelProvider.NewInstanceFactory()).get(cls)
        lifecycle.addObserver(viewModel)
        return viewModel
    }

    fun <T : BaseViewModel> createViewModelInFragment(cls: Class<T>): T {
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(cls)
        lifecycle.addObserver(viewModel)
        return viewModel
    }

}