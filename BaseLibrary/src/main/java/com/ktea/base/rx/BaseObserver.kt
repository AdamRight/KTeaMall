package com.ktea.base.rx
import com.ktea.base.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *  Rx订阅者默认实现
 */
open class BaseObserver<T>(private val baseView: BaseView) : Observer<T> {

    override fun onComplete() {
        baseView.hideLoading()
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
        baseView.hideLoading()
    }

    override fun onError(e: Throwable) {
        baseView.hideLoading()
        if (e is BaseException) {
            baseView.onError(e.msg)
        }
        /*else {
            baseView.onError("服务器异常")
        }*/
    }
}