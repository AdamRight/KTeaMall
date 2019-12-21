package com.ktea.user.service.impl

import com.ktea.user.service.UserService
import io.reactivex.Observable

/**
 * Created by jiangtea on 2019/12/21.
 */
class UserServiceImpl : UserService {
    override fun register(phone: String, code: String, pwd: String): Observable<Boolean> {

        return Observable.just(true)
    }
}