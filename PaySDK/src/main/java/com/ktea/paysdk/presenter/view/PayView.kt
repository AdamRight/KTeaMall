package com.ktea.paysdk.presenter.view

import com.ktea.base.presenter.view.BaseView

interface PayView : BaseView {
    //获取支付签名
    fun onGetSignResult(result: String)

    //同步支付成功状态
    fun onPayOrderResult(result: Boolean)
}