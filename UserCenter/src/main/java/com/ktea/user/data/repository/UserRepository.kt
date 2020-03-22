package com.ktea.user.data.repository

import com.ktea.base.data.net.RetrofitFactory
import com.ktea.base.data.protocol.BaseResp
import com.ktea.user.data.api.UserApi
import com.ktea.user.data.protocol.RegisterReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jiangtea on 2019/12/22.
 */
class UserRepository @Inject constructor() {
    /**
     * 用户注册
     */
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
    }

}