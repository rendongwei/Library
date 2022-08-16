package com.don.library.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class BaseLifecycleObserver(private val mListener: OnLifecycleListener? = null) :
    LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(owner: LifecycleOwner) {
        mListener?.onCreate(owner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(owner: LifecycleOwner) {
        mListener?.onStart(owner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(owner: LifecycleOwner) {
        mListener?.onResume(owner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(owner: LifecycleOwner) {
        mListener?.onPause(owner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(owner: LifecycleOwner) {
        mListener?.onStop(owner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        mListener?.onDestroy(owner)
    }

    interface OnLifecycleListener {
        fun onCreate(owner: LifecycleOwner) {}
        fun onStart(owner: LifecycleOwner) {}
        fun onResume(owner: LifecycleOwner) {}
        fun onPause(owner: LifecycleOwner) {}
        fun onStop(owner: LifecycleOwner) {}
        fun onDestroy(owner: LifecycleOwner) {}
    }
}