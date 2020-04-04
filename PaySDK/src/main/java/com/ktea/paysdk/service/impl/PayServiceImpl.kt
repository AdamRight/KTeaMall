package com.ktea.paysdk.service.impl

import com.ktea.base.ext.convert
import com.ktea.base.ext.convertBoolean
import com.ktea.paysdk.data.repository.PayRepository
import com.ktea.paysdk.service.PayService
import io.reactivex.Observable
import javax.inject.Inject

class PayServiceImpl @Inject constructor() : PayService {
    @Inject
    lateinit var repository: PayRepository

    /*
        获取支付签名
     */
    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String> {
        return repository.getPaySign(orderId, totalPrice).convert()
    }

    /*
       支付订单，同步订单状态
     */
    override fun payOrder(orderId: Int): Observable<Boolean> {
        return repository.payOrder(orderId).convertBoolean()
    }
}