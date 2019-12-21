package com.ktea.base.presenter

import com.ktea.base.presenter.view.BaseView

/**
 * Created by jiangtea on 2019/12/21.
 */
open class BasePresenter<T : BaseView> {
    lateinit var mView : T
}