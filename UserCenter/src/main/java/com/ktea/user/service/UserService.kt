package com.ktea.user.service

import io.reactivex.Observable


/**
 * Created by jiangtea on 2019/12/21.
 */
interface UserService {
    fun register(phone: String, code: String, pwd: String): Observable<Boolean>
}