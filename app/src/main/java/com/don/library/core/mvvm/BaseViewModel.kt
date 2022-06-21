package com.don.library.core.mvvm

import android.os.Handler
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.don.library.bean.BaseResponseBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import retrofit2.Response


open abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private val mViewModelJob = SupervisorJob()
    private val mViewModelScope = CoroutineScope(Dispatchers.Main + mViewModelJob)

    private val mLoopJobMap = mutableMapOf<String, Job>()
    private val mLoopHandlerMap = mutableMapOf<String, Handler>()

    fun <T : BaseResponseBean<E>, E> launch(
        block: suspend CoroutineScope.() -> Response<T>,
        error: suspend CoroutineScope.(Pair<String?, E?>) -> Unit = { },
        success: suspend CoroutineScope.(E?) -> Unit = {},
        start: suspend CoroutineScope.() -> Unit = { },
        complete: suspend CoroutineScope.() -> Unit = { },
        interval: Long = -1
    ): Job {
        return mViewModelScope.launch {
            start()
            try {
                if (isActive) {
                    var bean: BaseResponseBean<E>? = withContext(Dispatchers.IO) {
                        var response = block()
                        var result = response.body() ?: Gson().fromJson(
                            String(response.errorBody()?.bytes() ?: byteArrayOf()),
                            object : TypeToken<BaseResponseBean<E>>() {}.type
                        )
                        var url = response.raw().request().url().toString()
                        result?.loopUrl = url
                        result
                    }
                    if (bean?.isLogout() == true) {
                        return@launch
                    }
                    if (bean?.isSuccess() == true) {
                        success(bean.data)
                    } else {
                        error(Pair(bean?.message, bean))
                    }
                    var url = bean?.loopUrl
                    if (!url.isNullOrEmpty() && interval > 0) {
                        var job = mLoopJobMap[url]
                        var handler = mLoopHandlerMap[url]
                        job?.cancel()
                        handler?.removeMessages(0x10000)
                        mLoopJobMap.remove(url)
                        mLoopHandlerMap.remove(url)
                        var newHandler = Handler {
                            var newJob = launch(block, { }, success, { }, { }, interval)
                            mLoopJobMap[url] = newJob
                            true
                        }
                        newHandler.sendEmptyMessageDelayed(0x10000, interval)
                        mLoopHandlerMap[url] = newHandler
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                if (isActive) {
                    error(Pair("网络错误", null))
                }
            } finally {
                if (isActive) {
                    complete()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mLoopJobMap.forEach {
            it.value.cancel()
        }
        mLoopHandlerMap.forEach {
            it.value.removeMessages(0x10000)
        }
        mLoopJobMap.clear()
        mLoopHandlerMap.clear()
        mViewModelScope.cancel()
    }
}