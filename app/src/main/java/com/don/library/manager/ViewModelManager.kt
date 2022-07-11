package com.don.library.manager

import androidx.lifecycle.*
import com.don.library.core.mvvm.BaseViewModel
import com.don.library.extend.createViewModel

object ViewModelManager : LifecycleOwner, ViewModelStoreOwner {

    private val mLifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }
    private val mViewModelStore by lazy {
        ViewModelStore()
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

    override fun getViewModelStore(): ViewModelStore {
        return mViewModelStore
    }

    fun onCreate() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        if (mLifecycleRegistry.currentState == Lifecycle.State.CREATED) {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        } else {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }
    }

    fun onDestroy() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    fun <T : BaseViewModel> create(cls: Class<T>): T {
        return createViewModel(this, cls)
    }
}