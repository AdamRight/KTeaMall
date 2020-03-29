package com.ktea.goods.injection.module

import com.ktea.goods.service.CategoryService
import com.ktea.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by jiangtea on 2020/3/29.
 */
@Module
class CategoryModule {

    @Provides
    fun providerCategoryService(categoryService: CategoryServiceImpl): CategoryService {
        return categoryService
    }
}