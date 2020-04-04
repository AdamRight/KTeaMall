package com.kotlin.order.data.protocol

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
   订单中的商品
 */
@Parcelize
data class OrderGoods(
        val id: Int,
        var goodsId: Int,
        val goodsDesc: String,
        val goodsIcon: String,
        val goodsPrice: Long,
        val goodsCount: Int,
        val goodsSku: String,
        val orderId: Int
) : Parcelable

