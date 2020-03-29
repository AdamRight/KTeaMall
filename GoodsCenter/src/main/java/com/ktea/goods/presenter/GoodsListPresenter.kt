package com.ktea.goods.presenter

import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.goods.data.protocol.Goods
import com.ktea.goods.presenter.view.GoodsListView
import com.ktea.goods.service.GoodsService
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/29.
 */
class GoodsListPresenter@Inject constructor() : BasePresenter<GoodsListView>() {

    @Inject
    lateinit var goodsService: GoodsService

    /*
    获取商品列表
     */
    fun getGoodsList(categoryId: Int, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsList(categoryId, pageNo)
                .execute(object : BaseObserver<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                        super.onNext(t)
                        mView.onGetGoodsListResult(t)
                    }
                }, lifecycleProvider)
    }

    /*
    根据关键字 搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsListByKeyword(keyword, pageNo)
                .execute(object : BaseObserver<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                        super.onNext(t)
                        mView.onGetGoodsListResult(t)
                    }
                }, lifecycleProvider)
    }
}