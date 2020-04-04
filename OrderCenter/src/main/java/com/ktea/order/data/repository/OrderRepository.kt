package com.ktea.order.data.repository

import com.kotlin.order.data.api.OrderApi
import com.kotlin.order.data.protocol.*
import com.ktea.base.data.net.RetrofitFactory
import com.ktea.base.data.protocol.BaseResp
import io.reactivex.Observable
import javax.inject.Inject

class OrderRepository @Inject constructor() {
    /*
       取消订单
    */
    fun cancelOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .cancelOrder(CancelOrderReq(orderId))
    }

    /*
        确认订单
     */
    fun confirmOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .confirmOrder(ConfirmOrderReq(orderId))
    }
    /*
       根据ID查询订单
    */
    fun getOrderById(orderId: Int): Observable<BaseResp<Order>> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .getOrderById(GetOrderByIdReq(orderId))
    }

    /*
        根据状态查询订单列表
     */
    fun getOrderList(orderStatus: Int): Observable<BaseResp<MutableList<Order>?>> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .getOrderList(GetOrderListReq(orderStatus))
    }

    /*
        提交订单
     */
    fun submitOrder(order: Order): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .submitOrder(SubmitOrderReq(order))
    }
}