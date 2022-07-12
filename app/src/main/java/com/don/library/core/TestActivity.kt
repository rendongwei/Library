package com.don.library.core

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.don.library.R
import com.don.library.bean.BaseResponseBean
import com.don.library.core.activity.BaseStatusBarActivity
import com.don.library.core.adapter.BaseMultipleAdapter
import com.don.library.core.mvvm.BaseViewModel
import com.don.library.extend.initLinearLayoutManager
import com.don.library.extend.loadImage
import com.don.library.manager.BaseHttpManager
import com.don.library.manager.ViewModelManager
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.item_a.view.*
import kotlinx.android.synthetic.main.item_b.view.*
import retrofit2.Response
import retrofit2.http.GET

class TestActivity : BaseStatusBarActivity() {

    private val mViewModel by lazy {
        ViewModelManager.create(com.don.library.core.ViewModel::class.java)
    }

    override fun getContentView(): Int {
        return R.layout.activity_test
    }

    override fun initListener() {
        mViewModel.mData.observe(this) {
            println("TestActivity:$it")
        }
        aa.setOnClickListener {
            startActivity(Intent(this, BActivity::class.java))
        }
    }

    override fun init() {
        ll.initLinearLayoutManager().adapter = Adapter(this, mutableListOf("", "", "", "", "", ""))
    }

    class Adapter(context: Context, list: MutableList<String>?) :
        BaseMultipleAdapter<String>(context, list) {

        override fun getItemType(position: Int): Int {
            return position % 2
        }

        override fun getContentViews(): Map<Int, Int> {
            val map = hashMapOf<Int, Int>()
            map[0] = R.layout.item_a
            map[1] = R.layout.item_b
            return map
        }

        override fun bindView(view: View, position: Int, t: String) {
            with(view) {
                when (getItemType(position)) {
                    0 -> {
                        aaaa.text = "哈哈--${position}"
                    }
                    1 -> {
                        bbbb.loadImage("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp09%2F210F2130512J47-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660116698&t=a952f415857d1dc7e132e21fdb4a1110")
                    }
                    else -> {

                    }
                }
            }

        }


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