package com.wuc.kotlin.user.injection.module

import com.ktea.user.service.UserService
import com.ktea.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides


@Module
class UserModule {

    @Provides
    fun provideUserService(service: UserServiceImpl): UserService {
        return service
    }
}