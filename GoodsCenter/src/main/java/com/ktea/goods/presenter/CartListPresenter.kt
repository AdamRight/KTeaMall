package com.ktea.goods.presenter

import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.goods.data.protocol.CartGoods
import com.ktea.goods.presenter.view.CartListView
import com.ktea.goods.service.CartService
import javax.inject.Inject


class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {

    @Inject
    lateinit var cartService: CartService

    /*
        获取购物车列表
     */
    fun getCartList() {
        if (!checkNetWork()) {
            return
        }
        //mView.showLoading()
        cartService.getCartList()
                .execute(object : BaseObserver<MutableList<CartGoods>?>(mView) {
                    override fun onNext(t: MutableList<CartGoods>?) {
                        super.onNext(t)
                        mView.onGetCartListResult(t)
                    }
                }, lifecycleProvider)
    }

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.deleteCartList(list)
                .execute(object : BaseObserver<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        mView.onDeleteCartListResult(t)
                    }
                }, lifecycleProvider)
    }

    /*
      提交购物车商品
   */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.submitCart(list, totalPrice)
                .execute(object : BaseObserver<Int>(mView) {
                    override fun onNext(t: Int) {
                        mView.onSubmitCartListResult(t)
                    }
                }, lifecycleProvider)

    }
}