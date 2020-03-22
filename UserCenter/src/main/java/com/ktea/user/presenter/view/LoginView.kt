package com.ktea.user.presenter.view

import com.ktea.base.presenter.view.BaseView
import com.ktea.user.data.protocol.UserInfo

/**
 * Created by jiangtea on 2019/12/21.
 */
interface LoginView : BaseView {
    fun onLoginResult(result: UserInfo)
}