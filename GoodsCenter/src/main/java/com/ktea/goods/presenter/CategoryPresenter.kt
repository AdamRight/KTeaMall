package com.ktea.goods.presenter

import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.goods.data.protocol.Category
import com.ktea.goods.presenter.view.CategoryView
import com.ktea.goods.service.CategoryService
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/29.
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    /**
     * 获取商品分类列表
     */
    fun getCategory(parentId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        categoryService.getCategory(parentId)
                .execute(object : BaseObserver<MutableList<Category>?>(mView) {
                    override fun onNext(t: MutableList<Category>?) {
                        super.onNext(t)
                        mView.onGetCategoryResult(t)
                    }
                }, lifecycleProvider)
    }
}