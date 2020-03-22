package com.ktea.user.data.api

import com.ktea.base.data.protocol.BaseResp
import com.ktea.user.data.protocol.LoginReq
import com.ktea.user.data.protocol.RegisterReq
import com.ktea.user.data.protocol.UserInfo
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by jiangtea on 2019/12/22.
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq) : Observable<BaseResp<String>>
    /**
     *  用户登录
     */
    @POST("userCenter/login")
    fun login(@Body req: LoginReq): Observable<BaseResp<UserInfo>>
}