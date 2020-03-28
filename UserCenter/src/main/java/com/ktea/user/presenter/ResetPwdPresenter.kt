package com.ktea.user.presenter

import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.user.presenter.view.ResetPwdView
import com.ktea.user.service.UserService
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/28.
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserService

    /*
    重置密码
     */
    fun resetPwd(mobile: String, pwd: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.resetPwd(mobile, pwd)
                .execute(object : BaseObserver<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        if (t)
                            mView.onResetPwdResult("重置密码成功")
                    }
                }, lifecycleProvider)
    }
}