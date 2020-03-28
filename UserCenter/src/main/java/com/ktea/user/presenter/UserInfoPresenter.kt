package com.ktea.user.presenter

import com.ktea.base.ext.execute
import com.ktea.base.presenter.BasePresenter
import com.ktea.base.rx.BaseObserver
import com.ktea.user.data.protocol.UserInfo
import com.ktea.user.presenter.view.UserInfoView
import com.ktea.user.service.UploadService
import com.ktea.user.service.UserService
import javax.inject.Inject

/**
 * Created by jiangtea on 2020/3/28.
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService
    @Inject
    lateinit var uploadService: UploadService

    /**
     * 获取七牛云上传凭证
     */
    fun getUploadToken() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        uploadService.getUploadToken()
                .execute(object : BaseObserver<String>(mView) {
                    override fun onNext(t: String) {
                        super.onNext(t)
                        mView.onGetUploadTokenResult(t)
                    }
                }, lifecycleProvider)
    }

    /**
     * 编辑用户资料
     */
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.editUser(userIcon, userName, userGender, userSign)
                .execute(object : BaseObserver<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        super.onNext(t)
                        mView.onEditUserResult(t)
                    }
                }, lifecycleProvider)
    }
}