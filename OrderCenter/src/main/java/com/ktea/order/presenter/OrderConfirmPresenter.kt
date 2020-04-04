package com.ktea.order.presenter

import com.kotlin.order.data.protocol.Order
import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.order.presenter.view.OrderConfirmView
import com.ktea.order.service.OrderService
import javax.inject.Inject


class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

    @Inject
    lateinit var orderService: OrderService

    /*
        根据ID获取订单
     */
    fun getOrderById(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId)
                .execute(object : BaseObserver<Order>(mView) {
                    override fun onNext(t: Order) {
                        super.onNext(t)
                        mView.onGetOrderByIdResult(t)
                    }
                }, lifecycleProvider)
    }

    /*
        提交订单
     */
    fun submitOrder(order: Order) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.submitOrder(order)
                .execute(object : BaseObserver<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        mView.onSubmitOrderResult(t)
                    }
                }, lifecycleProvider)
    }
}