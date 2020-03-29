package com.ktea.goods.injection.component

import com.ktea.base.injection.PerComponentScope
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.goods.injection.module.CartModule
import com.ktea.goods.injection.module.GoodsModule
import com.ktea.goods.ui.activity.GoodsActivity
import com.ktea.goods.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * Created by jiangtea on 2020/3/29.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(GoodsModule::class, CartModule::class))
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
}