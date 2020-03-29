package com.ktea.goods.service

import com.ktea.goods.data.protocol.Goods
import io.reactivex.Observable

/**
 * Created by jiangtea on 2020/3/29.
 */
interface GoodsService {
    /**
     * 获取商品列表
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>

    /**
     *根据关键字查询商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>

    /**
     * 获取商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}