package com.ktea.goods.presenter.view

import com.ktea.base.presenter.view.BaseView
import com.ktea.goods.data.protocol.CartGoods


interface CartListView : BaseView {
    /*
        获取购物车列表
     */
    fun onGetCartListResult(result: MutableList<CartGoods>?)

    /*
        删除购物车
     */
    fun onDeleteCartListResult(result: Boolean)

    /*
        提交购物车
     */
    fun onSubmitCartListResult(result: Int)
}