package com.ktea.order.presenter.view

import com.kotlin.order.data.protocol.ShipAddress
import com.ktea.base.presenter.view.BaseView


interface ShipAddressView : BaseView {

    //获取收货人列表回调
    fun onGetShipAddressResult(result: MutableList<ShipAddress>?)

    //设置默认收货人回调
    fun onSetDefaultResult(result: Boolean)

    //删除收货人回调
    fun onDeleteResult(result: Boolean)
}