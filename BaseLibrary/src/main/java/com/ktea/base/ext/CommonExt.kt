package com.ktea.base.ext

import com.ktea.base.rx.BaseSubscribe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jiangtea on 2019/12/21.
 */
fun <T> Observable<T>.execute(subscribe: BaseSubscribe<T>){
    this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(subscribe)
}