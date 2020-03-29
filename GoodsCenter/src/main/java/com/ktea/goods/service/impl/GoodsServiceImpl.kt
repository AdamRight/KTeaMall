package com.ktea.goods.service.impl

import com.ktea.base.ext.convert
import com.ktea.goods.data.protocol.Goods
import com.ktea.goods.data.repository.GoodsRepository
import com.ktea.goods.service.GoodsService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/29.
 */
class GoodsServiceImpl @Inject constructor() : GoodsService {

    @Inject
    lateinit var repository: GoodsRepository

    /**
     *获取商品列表
     */
    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsList(categoryId, pageNo).convert()
    }

    /**
     *根据关键字查询商品
     */
    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsListByKeyword(keyword, pageNo).convert()
    }

    /**
     *获取商品详情
     */
    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
        return repository.getGoodsDetail(goodsId).convert()
    }
}