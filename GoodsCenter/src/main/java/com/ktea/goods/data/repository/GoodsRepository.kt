package com.ktea.goods.data.repository

import com.ktea.base.data.net.RetrofitFactory
import com.ktea.base.data.protocol.BaseResp
import com.ktea.goods.data.api.GoodsApi
import com.ktea.goods.data.protocol.GetGoodsDetailReq
import com.ktea.goods.data.protocol.GetGoodsListByKeywordReq
import com.ktea.goods.data.protocol.GetGoodsListReq
import com.ktea.goods.data.protocol.Goods
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/29.
 */
class GoodsRepository @Inject constructor() {

    /**
     * 根据分类搜索商品
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsList(GetGoodsListReq(categoryId, pageNo))
    }

    /**
     * 根据关键字搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsListByKeyword(GetGoodsListByKeywordReq(keyword, pageNo))
    }

    /**
     * 商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<BaseResp<Goods>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsDetail(GetGoodsDetailReq(goodsId))
    }

}