package com.ktea.goods.injection.component

import com.ktea.base.injection.PerComponentScope
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.goods.injection.module.CartModule
import com.ktea.goods.ui.fragment.CartFragment
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(CartModule::class))
interface CartComponent {
    fun inject(fragment: CartFragment)
}