package com.ktea.goods.presenter

import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.base.utils.AppPrefsUtils
import com.ktea.goods.common.GoodsConstant
import com.ktea.goods.data.protocol.Goods
import com.ktea.goods.presenter.view.GoodsDetailView
import com.ktea.goods.service.CartService
import com.ktea.goods.service.GoodsService
import javax.inject.Inject

class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService
    @Inject
    lateinit var cartService: CartService

    /*
        获取商品详情
     */
    fun getGoodsDetailList(goodsId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId)
                .execute(object : BaseObserver<Goods>(mView) {
                    override fun onNext(t: Goods) {
                        super.onNext(t)
                        mView.onGetGoodsDetailResult(t)
                    }
                }, lifecycleProvider)
    }

    /*
        加入购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku)
                .execute(object : BaseObserver<Int>(mView) {
                    override fun onNext(t: Int) {
                        super.onNext(t)
                        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, t)
                        mView.onAddCartResult(t)
                    }
                }, lifecycleProvider)
    }
}