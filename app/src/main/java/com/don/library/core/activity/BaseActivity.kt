package com.don.library.core.activity

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.don.library.core.mvvm.BaseViewModel
import com.don.library.extend.createViewModel
import com.don.library.extend.layout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    // Application上下文
    protected val mApplicationContext by lazy { applicationContext }

    // Activity
    protected val mActivity by lazy { this }

    // Context上下文
    protected val mContext: Context by lazy { this }

    // 保存状态
    protected var mSaveInstanceState: Bundle? = null

    // 当前界面View
    private var mContentView: View? = null

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

    // 是否修改屏幕方向为竖屏
    open fun isChangeScreenOrientationPortrait(): Boolean {
        return true
    }

    open fun setDataBindingView(): View? {
        return null
    }

    open fun isOpenEventBus(): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSaveInstanceState = savedInstanceState
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        if (isChangeScreenOrientationPortrait()) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        // 设置底部栏
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.navigationBarColor = Color.WHITE

        mContentView = setDataBindingView()
        if (mContentView == null) {
            mContentView = getCustomContentView() ?: layout(getContentView())
        }

        setContentView(mContentView)

        if (isOpenEventBus()) {
            EventBus.getDefault().register(this)
        }
        initListener()
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isOpenEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        cancel()
    }

    fun <T : BaseViewModel> createViewModel(cls: Class<T>): T {
        return createViewModel(this, cls)
    }
}