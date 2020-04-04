package com.ktea.order.injection.component

import com.ktea.base.injection.PerComponentScope
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.order.injection.module.OrderModule
import com.ktea.order.ui.activity.OrderConfirmActivity
import com.ktea.order.ui.activity.OrderDetailActivity
import com.ktea.order.ui.fragment.OrderFragment


import dagger.Component

@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)],
        modules = [(OrderModule::class)])
interface OrderComponent {
    fun inject(activity: OrderConfirmActivity)
    fun inject(fragment: OrderFragment)
    fun inject(activity: OrderDetailActivity)
}