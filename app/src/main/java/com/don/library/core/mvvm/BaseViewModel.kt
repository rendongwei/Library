package com.don.library.core.mvvm

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.don.library.bean.BaseResponseBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import retrofit2.Response


open class BaseViewModel : ViewModel(), LifecycleObserver, CoroutineScope by MainScope() {

    private val mLooper: Looper? = Looper.getMainLooper()
    private val mLoopJobMap = mutableMapOf<String, Job>()
    private val mLoopHandlerMap = mutableMapOf<String, Handler>()


    fun <T : BaseResponseBean<E>, E> launch(
        block: suspend CoroutineScope.() -> Response<T>,
        error: suspend CoroutineScope.(Pair<String?, BaseResponseBean<E>?>) -> Unit = { },
        success: suspend CoroutineScope.(E?) -> Unit = {},
        start: suspend CoroutineScope.() -> Unit = { },
        complete: suspend CoroutineScope.() -> Unit = { },
        interval: Long = -1
    ): Job {
        return launch {
            start()
            try {
                if (isActive) {
                    var bean: T? = withContext(Dispatchers.IO) {
                        var response = block()
                        var result = response.body() ?: Gson().fromJson(
                            String(response.errorBody()?.bytes() ?: byteArrayOf()),
                            object : TypeToken<T>() {}.type
                        )
                        var url = response.raw().request().url().toString()
                        result?.intervalUrl = url
                        result
                    }
                    if (bean?.isLogout() == true) {
                        return@launch
                    }
                    if (bean?.isSuccess() == true) {
                        success(bean.getResponse())
                    } else {
                        error(Pair(bean?.getErrorMessage(), bean))
                    }
                    var url = bean?.intervalUrl
                    if (!url.isNullOrEmpty() && interval > 0) {
                        var job = mLoopJobMap[url]
                        var handler = mLoopHandlerMap[url]
                        job?.cancel()
                        handler?.removeMessages(0x10000)
                        mLoopJobMap.remove(url)
                        mLoopHandlerMap.remove(url)
                        var newHandler = Handler(mLooper) {
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
        cancel()
    }
}