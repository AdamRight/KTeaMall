package com.ktea.user.presenter.view

import com.ktea.base.presenter.view.BaseView
import com.ktea.user.data.protocol.UserInfo

/**
 * Created by jiangtea on 2020/3/28.
 */
interface UserInfoView : BaseView {
    /**
     * 获取上传凭证回调
     */
    fun onGetUploadTokenResult(result: String)

    /**
     * 编辑用户资料回调
     */
    fun onEditUserResult(result: UserInfo)
}