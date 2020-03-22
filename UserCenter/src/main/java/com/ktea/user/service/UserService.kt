package com.ktea.user.service

import com.ktea.user.data.protocol.UserInfo
import io.reactivex.Observable


/**
 * Created by jiangtea on 2019/12/21.
 */
interface UserService {
    //用户注册
    fun register(mobile: String, pwd: String, verityCode: String): Observable<Boolean>
    //用户登录
    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>


}