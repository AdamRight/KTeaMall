package com.ktea.goods.data.protocol

/**
 * Created by jiangtea on 2020/3/29.
 */
data class GetGoodsListByKeywordReq(
        val keyword: String,
        val pageNo: Int
)