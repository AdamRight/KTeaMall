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
    //忘记密码
    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>
    //重置密碼
    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>
    //编辑用户资料
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo>
}