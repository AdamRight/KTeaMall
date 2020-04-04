package com.ktea.order.presenter.view

import com.kotlin.order.data.protocol.Order
import com.ktea.base.presenter.view.BaseView


interface OrderConfirmView : BaseView {

    //获取订单回调
    fun onGetOrderByIdResult(result: Order)

    //提交订单回调
    fun onSubmitOrderResult(result: Boolean)
}