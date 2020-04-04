package com.ktea.message.center.injection.component


import com.ktea.base.injection.PerComponentScope
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.message.center.injection.module.MessageModule
import com.ktea.message.center.ui.fragment.MessageFragment
import dagger.Component

/*
    消息模块注入组件
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)],
        modules = [(MessageModule::class)])
interface MessageComponent {
    fun inject(fragment: MessageFragment)
}
