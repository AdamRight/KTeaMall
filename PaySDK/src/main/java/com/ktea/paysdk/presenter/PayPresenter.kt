package com.ktea.paysdk.presenter

import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.paysdk.presenter.view.PayView
import com.ktea.paysdk.service.PayService

import javax.inject.Inject


class PayPresenter @Inject constructor() : BasePresenter<PayView>() {

    @Inject
    lateinit var service: PayService

    /*
            获取支付签名
         */
    fun getPaySign(orderId: Int, totalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.getPaySign(orderId, totalPrice).execute(object : BaseObserver<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetSignResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        订单支付，同步订单状态
     */
    fun payOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.payOrder(orderId).execute(object : BaseObserver<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onPayOrderResult(t)
            }
        }, lifecycleProvider)

    }
}