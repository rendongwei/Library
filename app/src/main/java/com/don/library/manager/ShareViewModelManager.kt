package com.don.library.manager

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import com.don.library.core.mvvm.BaseViewModel
import com.don.library.extend.createViewModel
import com.don.library.lifecycle.BaseLifecycleObserver
import com.don.library.util.LogUtil

object ShareViewModelManager {

    private const val TAG = "ShareViewModelManager"
    private val mLifecycleOwners: MutableMap<String?, LifecycleOwner> = mutableMapOf()
    private val mViewModelStoreOwners: MutableMap<LifecycleOwner, ViewModelStoreOwner> =
        mutableMapOf()

    fun register(activity: AppCompatActivity) {
        val lifecycleOwner: LifecycleOwner = activity
        val viewModelStoreOwner: ViewModelStoreOwner = activity
        mLifecycleOwners[activity::class.java.canonicalName] = lifecycleOwner
        mViewModelStoreOwners[lifecycleOwner] = viewModelStoreOwner
        lifecycleOwner.lifecycle.addObserver(BaseLifecycleObserver(object :
            BaseLifecycleObserver.OnLifecycleListener {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                var key: String? = null
                mLifecycleOwners.entries.forEach {
                    if (it.value == owner) {
                        key = it.key
                        return@forEach
                    }
                }
                key?.apply {
                    mLifecycleOwners.remove(this)
                }
                mViewModelStoreOwners.remove(owner)
                LogUtil.log(
                    TAG,
                    "unRegister:${activity::class.java.canonicalName}  mLifecycleOwners:${mLifecycleOwners.size}   mViewModelStoreOwners:${mViewModelStoreOwners.size}"
                )
            }
        }))
        LogUtil.log(TAG, "register:${activity::class.java.canonicalName}")
    }

    fun <E, T : BaseViewModel> AppCompatActivity.createShareViewModel(
        activity: Class<E>,
        cls: Class<T>
    ): T {
        var lifecycleOwner =
            mLifecycleOwners[activity.canonicalName]
        if (lifecycleOwner == null) {
            lifecycleOwner = this
            LogUtil.log(
                TAG,
                "createShareViewModel:${activity.canonicalName}  未找到lifecycleOwner"
            )
        } else {
            LogUtil.log(
                TAG,
                "createShareViewModel:${activity.canonicalName}  获取到lifecycleOwner"
            )
        }
        var viewModelStoreOwner = mViewModelStoreOwners[lifecycleOwner]
        if (viewModelStoreOwner == null) {
            viewModelStoreOwner = this
            LogUtil.log(
                TAG,
                "createShareViewModel:${activity.canonicalName}  未找到viewModelStoreOwner"
            )
        } else {
            LogUtil.log(
                TAG,
                "createShareViewModel:${activity.canonicalName}  获取到viewModelStoreOwner"
            )
        }
        return createViewModel(viewModelStoreOwner, cls)
    }
}