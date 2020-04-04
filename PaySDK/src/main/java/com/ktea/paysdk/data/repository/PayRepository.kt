package com.ktea.paysdk.data.repository

import com.ktea.base.data.net.RetrofitFactory
import com.ktea.base.data.protocol.BaseResp
import com.ktea.paysdk.data.api.PayApi
import com.ktea.paysdk.data.protocol.GetPaySignReq
import com.ktea.paysdk.data.protocol.PayOrderReq
import io.reactivex.Observable
import javax.inject.Inject

class PayRepository @Inject constructor() {
    /*
        获取支付宝支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java)
                .getPaySign(GetPaySignReq(orderId, totalPrice))
    }

    /*
        刷新订单状态已支付
     */
    fun payOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java)
                .payOrder(PayOrderReq(orderId))
    }
}