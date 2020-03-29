package com.ktea.goods.injection.component

import com.ktea.base.injection.PerComponentScope
import com.ktea.base.injection.component.ActivityComponent
import com.ktea.goods.injection.module.CategoryModule
import com.ktea.goods.ui.fragment.CategoryFragment
import dagger.Component

/**
 * Created by jiangtea on 2020/3/29.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}