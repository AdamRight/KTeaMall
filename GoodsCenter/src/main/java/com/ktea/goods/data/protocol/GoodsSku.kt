package com.ktea.goods.data.protocol

/**
 * Created by jiangtea on 2020/3/29.
 */
data class GoodsSku(
        val id: Int,
        val skuTitle: String,//SKU标题
        val skuContent: List<String>//SKU内容
)