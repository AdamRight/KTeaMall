package com.ktea.user.service.impl

import com.ktea.base.ext.convert
import com.ktea.base.ext.convertBoolean
import com.ktea.user.data.protocol.UserInfo
import com.ktea.user.data.repository.UserRepository
import com.ktea.user.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jiangtea on 2019/12/21.
 */

class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    /**
     * 注册
     */
    override fun register(mobile: String, pwd: String, verityCode: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verityCode)
                .convertBoolean()
    }

    /**
     * 登录
     */
    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile, pwd, pushId).convert()
    }

}