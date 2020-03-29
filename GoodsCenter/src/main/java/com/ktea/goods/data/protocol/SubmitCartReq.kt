package com.ktea.goods.data.protocol

/**
 * Created by jiangtea on 2020/3/29.
 */
data class SubmitCartReq(val goodsList: List<CartGoods>, val totalPrice: Long)