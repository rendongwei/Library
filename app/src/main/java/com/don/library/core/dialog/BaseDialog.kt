package com.don.library.core.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.*

open class BaseDialog : Dialog, LifecycleOwner, ViewModelStoreOwner {
    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener)

    private val mLifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }
    private val mViewModelStore by lazy {
        ViewModelStore()
    }

    init {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (mLifecycleRegistry.currentState == Lifecycle.State.CREATED) {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        } else {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

    override fun getViewModelStore(): ViewModelStore {
        return mViewModelStore
    }
}