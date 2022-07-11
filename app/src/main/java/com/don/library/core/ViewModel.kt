package com.don.library.core

import androidx.lifecycle.MutableLiveData
import com.don.library.core.mvvm.BaseViewModel

class ViewModel : BaseViewModel() {

    var mData: MutableLiveData<String> = MutableLiveData()

}