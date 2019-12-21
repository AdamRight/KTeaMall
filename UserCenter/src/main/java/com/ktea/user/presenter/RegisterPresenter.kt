package com.ktea.user.presenter

import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseSubscribe
import com.ktea.user.presenter.view.RegisterView
import com.ktea.user.service.impl.UserServiceImpl

/**
 * Created by jiangtea on 2019/12/21.
 */
class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(phone: String, code: String, pwd: String) {

        //....业务处理
        val userServiceImpl = UserServiceImpl()
        userServiceImpl.register(phone, code, pwd)
                .execute(object : BaseSubscribe<Boolean>(){
                    override fun onNext(t: Boolean) {
                        mView.onResult(t)
                    }
                })


    }
}