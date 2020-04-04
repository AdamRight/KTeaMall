package com.ktea.order.presenter.view

import com.kotlin.order.data.protocol.Order
import com.ktea.base.presenter.view.BaseView

/*
    订单详情页 视图回调
 */
interface OrderDetailView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}
