package com.don.library.extend

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.don.library.core.mvvm.BaseViewModel

fun <T : BaseViewModel> LifecycleOwner.createViewModel(
    owner: ViewModelStoreOwner,
    cls: Class<T>
): T {
    val viewModel = ViewModelProvider(owner, ViewModelProvider.NewInstanceFactory()).get(cls)
    lifecycle.addObserver(viewModel)
    return viewModel
}