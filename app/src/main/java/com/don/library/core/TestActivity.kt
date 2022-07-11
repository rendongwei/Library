package com.don.library.core

import androidx.lifecycle.MutableLiveData
import com.don.library.R
import com.don.library.bean.BaseResponseBean
import com.don.library.core.activity.BaseStatusBarActivity
import com.don.library.core.mvvm.BaseViewModel
import com.don.library.manager.BaseHttpManager
import retrofit2.Response
import retrofit2.http.GET

class TestActivity : BaseStatusBarActivity() {

    override fun getContentView(): Int {
        return R.layout.activity_test
    }

    override fun initListener() {

    }

    override fun init() {
    }

    data class Version(var id: String, var version: String)
    class BaseResponse<T> : BaseResponseBean<T>() {
        private var code: Int = 0
        private var message: String? = null
        private var data: T? = null

        override fun isLogout(): Boolean {
            return false
        }

        override fun isSuccess(): Boolean {
            return code == 0
        }

        override fun getErrorMessage(): String? {
            return message
        }

        override fun getResponse(): T? {
            return data
        }

    }

    interface Service {
        @GET("/api/getVersion")
        suspend fun getVersion(): Response<BaseResponse<Version>>
    }

    object HttpManager : BaseHttpManager() {
        private val mRetrofit by lazy {
            createCoroutineRetrofit("http://www.rendongwei.cn:12580")
        }
        val mService by lazy {
            mRetrofit.create(Service::class.java)
        }
    }

    class ViewModel : BaseViewModel() {

        var mBean: MutableLiveData<Version> = MutableLiveData()

        fun get() {
            launch({
                HttpManager.mService.getVersion()
            }, {

            }, { bean ->
                mBean.value = bean
            })
        }
    }
}