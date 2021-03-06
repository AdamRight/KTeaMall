package com.ktea.goods.presenter.view

import com.ktea.base.presenter.view.BaseView
import com.ktea.goods.data.protocol.Goods

interface GoodsDetailView : BaseView {

    //获取商品详情
    fun onGetGoodsDetailResult(result: Goods)

    //加入购物车
    fun onAddCartResult(result: Int)
}