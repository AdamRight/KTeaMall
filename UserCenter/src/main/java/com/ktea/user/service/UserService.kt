package com.ktea.user.service

import io.reactivex.Observable


/**
 * Created by jiangtea on 2019/12/21.
 */
interface UserService {
    //用户注册
    fun register(mobile: String, pwd: String, verityCode: String): Observable<Boolean>
}