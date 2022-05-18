package com.don.library.manager

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object RxJavaManager {

    fun <T> create(block: ObservableEmitter<T>.() -> Unit, listener: (t: T) -> Unit): Disposable {
        return Observable.create<T> {
            block(it)
            it.onComplete()
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                listener.invoke(it)
            }
    }
}