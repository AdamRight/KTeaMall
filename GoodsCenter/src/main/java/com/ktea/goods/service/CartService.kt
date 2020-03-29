package com.ktea.goods.service

import com.ktea.goods.data.protocol.CartGoods
import io.reactivex.Observable


interface CartService {
    /**
     * 添加商品购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<Int>

    /**
     * 获取购物车列表
     */
    fun getCartList(): Observable<MutableList<CartGoods>?>

    /**
     * 删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<Boolean>

    /**
     * 提交购物车商品
     */
    fun submitCart(list: List<CartGoods>, totalPrice: Long): Observable<Int>
}