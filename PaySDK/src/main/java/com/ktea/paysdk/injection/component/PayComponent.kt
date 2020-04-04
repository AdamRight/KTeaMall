package com.ktea.paysdk.injection.component

import com.ktea.base.injection.PerComponentScope
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.paysdk.injection.module.PayModule
import com.ktea.paysdk.ui.activity.CashRegisterActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)]
        , modules = [(PayModule::class)])
interface PayComponent {
    fun inject(activity: CashRegisterActivity)
}