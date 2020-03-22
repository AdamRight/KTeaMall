package com.ktea.user.presenter

import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.user.data.protocol.UserInfo
import com.ktea.user.presenter.view.LoginView
import com.ktea.user.service.UserService
import javax.inject.Inject

/**
 * Created by jiangtea on 2019/12/21.
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {
    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, pwd: String, pushId: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.login(mobile, pwd, pushId)
                .execute(object : BaseObserver<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        super.onNext(t)
                        mView.onLoginResult(t)
                    }
                }, lifecycleProvider)
    }
}