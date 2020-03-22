package com.ktea.base.presenter.view

/**
 * Created by jiangtea on 2019/12/21.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(error: String)
}