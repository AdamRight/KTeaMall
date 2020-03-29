package com.ktea.goods.presenter.view

import com.ktea.base.presenter.view.BaseView
import com.ktea.goods.data.protocol.Goods

/**
 * Created by jiangtea on 2020/3/29.
 */
interface GoodsListView : BaseView {
    //获取商品列表
    fun onGetGoodsListResult(result: MutableList<Goods>?)
}