package com.ktea.user.presenter

import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.user.presenter.view.ForgetPwdView
import com.ktea.user.service.UserService
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/28.
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun forgetPwd(mobile: String, verifyCode: String) {
        if (!checkNetWork()) {
            return
        }
        userService.forgetPwd(mobile, verifyCode)
                .execute(object : BaseObserver<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        if (t)
                            mView.onForgetPwdResult("验证成功")
                    }
                }, lifecycleProvider)
    }
}