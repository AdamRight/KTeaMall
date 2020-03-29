package com.ktea.goods.injection.module

import com.ktea.goods.service.GoodsService
import com.ktea.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by jiangtea on 2020/3/29.
 */
@Module
class GoodsModule {
    @Provides
    fun providerGoodService(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }
}