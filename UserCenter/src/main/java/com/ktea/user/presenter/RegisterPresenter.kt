package com.ktea.user.presenter

import com.ktea.base.presenter.BasePresenter
import com.ktea.user.presenter.view.RegisterView

/**
 * Created by jiangtea on 2019/12/21.
 */
class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(phone : String ,code : String){

        //....业务处理

        mView.onResult(true)

    }
}