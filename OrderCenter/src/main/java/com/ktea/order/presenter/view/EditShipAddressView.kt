package com.ktea.order.presenter.view

import com.ktea.base.presenter.view.BaseView

interface EditShipAddressView : BaseView {

    //添加收货人回调
    fun onAddShipAddressResult(result: Boolean)

    //修改收货人回调
    fun onEditShipAddressResult(result: Boolean)
}