package com.ktea.goods.presenter.view

import com.ktea.base.presenter.view.BaseView
import com.ktea.goods.data.protocol.Category

/**
 * Created by jiangtea on 2020/3/29.
 */
interface CategoryView : BaseView {
    //获取商品分类列表
    fun onGetCategoryResult(result: MutableList<Category>?)
}