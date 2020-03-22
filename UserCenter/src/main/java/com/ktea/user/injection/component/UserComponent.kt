package com.ktea.user.injection.component

import com.ktea.base.injection.PerComponentScope
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.user.injection.module.UserModule
import com.ktea.user.ui.activity.LoginActivity
import com.ktea.user.ui.activity.RegisterActivity
import dagger.Component

/**
 * Created by jiangtea on 2020/3/22.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
}