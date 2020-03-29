package com.ktea.goods.data.api

import com.ktea.base.data.protocol.BaseResp
import com.ktea.goods.data.protocol.GetGoodsDetailReq
import com.ktea.goods.data.protocol.GetGoodsListByKeywordReq
import com.ktea.goods.data.protocol.GetGoodsListReq
import com.ktea.goods.data.protocol.Goods
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by jiangtea on 2020/3/29.
 */
interface GoodsApi {
    /**
     * 获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResp<MutableList<Goods>?>>

    /**
     * 获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /**
     * 获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResp<Goods>>
}