package com.don.library.core

import com.don.library.R
import com.don.library.core.activity.BaseStatusBarActivity
import com.don.library.manager.ViewModelManager
import kotlinx.android.synthetic.main.activity_test.*
import java.util.*

class BActivity : BaseStatusBarActivity() {

    private val mViewModel by lazy {
        ViewModelManager.create(com.don.library.core.ViewModel::class.java)
    }

    override fun getContentView(): Int {
        return R.layout.activity_test
    }

    override fun initListener() {
        mViewModel.mData.observe(this){
            println("BActivity:$it")
        }
        aa.setOnClickListener{
            mViewModel.mData.postValue(UUID.randomUUID().toString())
        }
    }

    override fun init() {

    }
}