package com.ktea.goods.service.impl

import com.ktea.base.ext.convert
import com.ktea.base.ext.convertBoolean
import com.ktea.goods.data.protocol.CartGoods
import com.ktea.goods.data.repository.CartRepository
import com.ktea.goods.service.CartService
import io.reactivex.Observable
import javax.inject.Inject


class CartServiceImpl @Inject constructor() : CartService {

    @Inject
    lateinit var cartRepository: CartRepository

    /**
     *获取购物车列表
     */
    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return cartRepository.getCartList().convert()
    }


    /**
     * 添加商品到购物车
     */
    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                         goodsCount: Int, goodsSku: String): Observable<Int> {
        return cartRepository.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku)
                .convert()
    }

    /**
     *删除购物车商品
     */
    override fun deleteCartList(list: List<Int>): Observable<Boolean> {
        return cartRepository.deleteCartList(list).convertBoolean()
    }

    /**
     * 提交购物车商品
     */
    override fun submitCart(list: List<CartGoods>, totalPrice: Long): Observable<Int> {
        return cartRepository.submitCart(list, totalPrice).convert()
    }

}