package com.ktea.base.rx

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * Created by jiangtea on 2019/12/21.
 */
open class BaseSubscribe<T> : Observer<T> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }

}