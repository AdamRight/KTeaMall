package com.ktea.order.injection.component

import com.ktea.base.injection.PerComponentScope
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.order.injection.module.ShipAddressModule
import com.ktea.order.ui.activity.ShipAddressActivity
import com.ktea.order.ui.activity.ShipAddressEditActivity
import dagger.Component


@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)],
        modules = [(ShipAddressModule::class)])
interface ShipAddressComponent {
    fun inject(activity: ShipAddressActivity)
    fun inject(activity: ShipAddressEditActivity)
}