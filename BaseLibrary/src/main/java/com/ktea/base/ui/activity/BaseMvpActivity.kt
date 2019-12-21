package com.ktea.base.ui.activity

import com.ktea.base.presenter.BasePresenter
import com.ktea.base.presenter.view.BaseView

/**
 * Created by jiangtea on 2019/12/21.
 */
open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mPresenter: T

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }
}