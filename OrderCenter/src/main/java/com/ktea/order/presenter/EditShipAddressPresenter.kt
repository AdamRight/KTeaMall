package com.ktea.order.presenter

import com.kotlin.order.data.protocol.ShipAddress
import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.order.presenter.view.EditShipAddressView
import com.ktea.order.service.ShipAddressService

import javax.inject.Inject


class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    /*
        添加收货人信息
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName, shipUserMobile, shipAddress)
                .execute(object : BaseObserver<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onAddShipAddressResult(t)
                    }
                }, lifecycleProvider)

    }

    /*
        修改收货人信息
     */
    fun editShipAddress(address: ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address)
                .execute(object : BaseObserver<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onEditShipAddressResult(t)
                    }
                }, lifecycleProvider)

    }
}